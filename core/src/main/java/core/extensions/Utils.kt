package core.extensions

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"