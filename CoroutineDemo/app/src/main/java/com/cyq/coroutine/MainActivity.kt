package com.cyq.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.liveData
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val scope = MainScope()
    private val ioScope = IoScheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Coroutines Camp ${Thread.currentThread().name}")
        GlobalScope.launch {
            println("Coroutines Camp 1 ${Thread.currentThread().name}")
        }

        Thread {
            println("Coroutines Camp 2 ${Thread.currentThread().name}")
        }.start()

        thread {
            println("Coroutines Camp 3 ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            ioFun1()
            mainFun2()
            ioFun3()
            mainFun4()
        }

        GlobalScope.launch(Dispatchers.Main) {
            val one = async { ioFun1() }
            val tow = async { mainFun2() }
            val same = one.await() == tow.await()
        }

        /**
         * ioFun1和 mainFun2并行执行完，才执行ioFun3
         */
        lifecycleScope.launch {
            coroutineScope {
                launch { ioFun1() }
                launch { mainFun2() }
            }
            ioFun3()
        }

        val repos: LiveData<List<String>> = liveData { emit(mainFun4()) }

        val kFunction0 = ::mainFun2

    }

    /**
     * 运行在io线程
     */
    private suspend fun ioFun1() {
        withContext(Dispatchers.IO) {
            println("Coroutines Camp io thread 1 ${Thread.currentThread().name}")
        }
        withContext(Dispatchers.Main) {
            tvTextView.text = "嘿嘿"
        }
    }

    /**
     * 运行在ui线程
     */
    private fun mainFun2() {
        println("Coroutines Camp ui thread 2 ${Thread.currentThread().name}")
    }

    /**
     * 运行在io线程
     */
    private suspend fun ioFun3() {
        withContext(Dispatchers.IO) {
            println("Coroutines Camp io thread 3 ${Thread.currentThread().name}")
        }
    }

    /**
     * 运行在ui线程
     */
    private fun mainFun4(): List<String> {
        println("Coroutines Camp ui thread 4 ${Thread.currentThread().name}")
        return arrayListOf()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}