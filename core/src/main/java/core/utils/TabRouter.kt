package core.utils
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

data class TabScreen(
    val firstController: Controller
)

class TabRouter(
    private val controller: Controller,
    private val navLayout: FrameLayout,
    startTabScreen: TabScreen
) {
    private val containers: MutableMap<TabScreen, ChangeHandlerFrameLayout> = hashMapOf()
    private val backStack: Deque<TabScreen> = LinkedList<TabScreen>()
    private var tabNavigationListener: ((TabScreen) -> Unit)? = null

    init {
        navigateTab(startTabScreen)
    }


    fun navigateTab(screen: TabScreen) {
        tabNavigationListener?.invoke(screen)
        screen.container()?.visibility = View.VISIBLE
        backStack.removeFirstOccurrence(screen)
        backStack.forEach { it.container()?.visibility = View.GONE }
        backStack.push(screen)
        val router = controller.getChildRouter(screen.container() as FrameLayout)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(screen.firstController))
        }
    }

    private fun TabScreen?.container() = this?.let {
        if (containers[it] == null) {
            createNewContainer(it)
        }
        containers[it]
    }

    private fun createNewContainer(screen: TabScreen) {
        val view = ChangeHandlerFrameLayout(controller.applicationContext)
        view.id = View.generateViewId()
        containers[screen] = view
        navLayout.addView(view)
    }

    fun handleBack(): Boolean {
        if (backStack.isNotEmpty()) {
            if (!backStack.first.firstController.handleBack()) {
                val screenLast = backStack.pollFirst()
                screenLast.container()?.visibility = View.GONE
                backStack.firstOrNull().container()?.visibility = View.VISIBLE
                backStack.firstOrNull()?.also { tabScreen ->
                    tabNavigationListener?.invoke(tabScreen)
                }
            }
        }
        return !backStack.isEmpty()
    }

    fun bindBottomNavigation(layout: BottomNavigationView, binding: BindingContext.() -> Unit) {
        val bindingContext = BindingContext()
        binding.invoke(bindingContext)
        val tabListener: (MenuItem) -> Boolean = {
            bindingContext.map[it.itemId]?.let {
                navigateTab(it)
                true
            } ?: false
        }
        layout.setOnNavigationItemSelectedListener(tabListener)
        tabNavigationListener = { navigatedScreen ->
            val newTab =
                bindingContext.map.entries.first { (_, screen) -> screen == navigatedScreen }.key
            if (newTab != layout.selectedItemId) {
                layout.setOnNavigationItemSelectedListener(null)
                layout.selectedItemId = newTab
                layout.setOnNavigationItemSelectedListener(tabListener)
            }
        }

    }
}

class BindingContext {
    internal val map: MutableMap<Int, TabScreen> = hashMapOf()
    infix fun TabScreen.to(menuId: Int) {
        map[menuId] = this
    }
}