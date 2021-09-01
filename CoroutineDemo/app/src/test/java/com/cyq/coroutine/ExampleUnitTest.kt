package com.cyq.coroutine

import android.util.Log
import dalvik.annotation.TestTarget
import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun main() {
        println("GlobalScope begin...")
        GlobalScope.launch {
            println("GlobalScope running...")
        }
        Thread.sleep(1000)
        println("GlobalScope end...")
    }

    @Test
    fun testRunBlocking() {
        //runBlocking会阻塞当前线程，知道作用域执行结束后释放
        runBlocking {
            launch {
                println("part 1 doing")
                delay(2000)
                println("part 1 complete")
            }
            launch {
                println("part 2 doing")
                delay(1000)
                println("part 2 complete")
            }
        }
        println("do finally...")
    }

    @Test
    fun testLaunch() {
        runBlocking {
            launch {
                println("part 1 doing")
                delay(2000)
                println("part 1 complete")
            }
            launch {
                println("part 2 doing")
                delay(1000)
                println("part 2 complete")
            }
            println("do finally...")
        }
    }

    /**
     * async它会创建一个新的子协程并返回一个Deferred对象，如果我们想要获取async函数代码块的执行结果，只需要调用Deferred对象的await()方法即可
     */
    @Test
    fun testAsync() {
        runBlocking {
            val a = 8
            val step1 = async {
                println("async 1先执行")
                a * 2
            }
            val step2 = async {
                println("async 2后执行")
                step1.await() * 3
            }
            println("final result = ${step2.await()}")
        }
    }

    @Test
    fun testCoroutineScope() {
        suspend fun f() = coroutineScope {
            launch {
                test()
            }
        }
        GlobalScope.launch {
            f()
        }
        Thread.sleep(1000)
    }

    private fun test() {
        println("----------")
    }
}