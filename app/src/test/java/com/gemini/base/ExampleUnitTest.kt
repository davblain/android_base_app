package com.gemini.base

import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun CharSequence.reversed(): String {
    val buf = StringBuilder(this.length)
    for (i in (length - 1) downTo 0) buf.append(get(i))
    return buf.toString()
}

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals("asdd", "ddsa".reversed())
    }
}
