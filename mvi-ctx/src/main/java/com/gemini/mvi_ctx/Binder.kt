

import androidx.lifecycle.Lifecycle
import com.gemini.mvi_ctx.Connection
import com.gemini.mvi_ctx.ui.UiScopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.lifecycle.events


class Binder(
    private val lifecycle: Lifecycle
) {
    private val scope = UiScopeProvider()
    private var isActive = false
    private val connections = mutableListOf<Connection<*, *>>()

    init {
        lifecycle.events()
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    Lifecycle.Event.ON_CREATE -> bindConnections()
                    Lifecycle.Event.ON_DESTROY -> unbindConnections()
                }
            }
            .launchIn(CoroutineScope(Dispatchers.Main))
    }


    private fun bindConnections() {
        isActive = true
        connections.forEach { connection ->
            subscribeWithLifecycle(connection as Connection<Any, Any>)
        }
    }

    fun <T : Any> bind(connection: Pair<Producer<out T>, Consumer<in T>>) {
        bind(
            Connection(
                from = connection.first,
                to = connection.second,
                connector = null
            )
        )
    }

    fun <Out : Any, In : Any> bind(connection: Connection<Out, In>) {
        val consumer = connection.to
        connections += connection
        if (isActive) {
            subscribeWithLifecycle(connection)
        }
    }

    private fun <Out, In> subscribeWithLifecycle(
        connection: Connection<Out, In>
    ) {
        connection.from?.getFlow()
            ?.onEach { out ->
                connection.connector?.let {
                    connection.to.accept(connection.connector.invoke(out))
                } ?: connection.to.accept(out as In)
            }?.launchIn(scope)

    }

    private fun unbindConnections() {
        isActive = false
        scope.clear()
    }

}