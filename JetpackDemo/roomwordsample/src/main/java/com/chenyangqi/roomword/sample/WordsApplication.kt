package com.chenyangqi.roomword.sample

import android.app.Application
import com.chenyangqi.roomword.sample.data.WordRepository
import com.chenyangqi.roomword.sample.data.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * @describe
 * @author chenyangqi
 * @time 2022/1/19 15:07
 */
class WordsApplication : Application() {
    //初始化数据库
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }

    override fun onCreate() {
        super.onCreate()

    }
}