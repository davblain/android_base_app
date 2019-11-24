package core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.bluelinelabs.conductor.archlifecycle.LifecycleController
import core.di.DI

import core.extensions.objectScopeName
import timber.log.Timber
import toothpick.Scope
import toothpick.ktp.KTP


abstract class BaseController(@LayoutRes private val layoutRes: Int) : LifecycleController(),
    ParentScopeProvider {

    private var fragmentScopeName: String = objectScopeName()

    protected open val parentScopeName: String by lazy {
        (parentController as? ParentScopeProvider)?.getParentScope() ?: DI.APP_SCOPE
    }

    protected inline fun <reified T> fromScope() = scope.getInstance(T::class.java)

    private val TAG: String
        get() = javaClass.simpleName

    protected open val customFragmentScopeName = ""

    lateinit var scope: Scope

    protected open fun installModules(scope: Scope) = Unit

    protected open fun initializeView(view: View): Unit = Unit

    override fun getParentScope(): String = fragmentScopeName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        log("onCreate")
        fragmentScopeName = if (customFragmentScopeName.isEmpty()) {
            objectScopeName()
        } else {
            customFragmentScopeName
        }
        if (KTP.isScopeOpen(fragmentScopeName)) {
            scope = KTP.openScope(fragmentScopeName)
        } else {
            scope = KTP.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }
        val view = inflater.inflate(layoutRes, container, false)
        initializeView(view)
        return view
    }

    override fun onDestroy() {
        log("onDestroy")
        KTP.closeScope(scope)
        super.onDestroy()
    }

    private fun log(log: String) {
        Timber.d("$TAG - $log")
    }
}