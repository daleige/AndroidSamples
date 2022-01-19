package com.cyq.coroutine

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cyq.coroutine", appContext.packageName)
    }

    @Test
    fun testWithContext() {
        runBlocking {
            val result = withContext(Dispatchers.IO) {
                delay(2000)
                "this result value = 5"
            }
            withContext(Dispatchers.Main) {
                println(Thread.currentThread().toString() + "=${result}")
            }
        }
    }
}