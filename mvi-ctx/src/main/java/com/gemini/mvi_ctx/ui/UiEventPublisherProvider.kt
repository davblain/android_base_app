
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class UiEventPublisherProvider<T>: UiEventPublisher<T> {

   private  val uiEventChanel: BroadcastChannel<T> =  BroadcastChannel(10)

    override fun getFlow(): Flow<T>  = uiEventChanel.asFlow()

    override suspend fun emit(event: T) {
        uiEventChanel.send(event);
    }

}