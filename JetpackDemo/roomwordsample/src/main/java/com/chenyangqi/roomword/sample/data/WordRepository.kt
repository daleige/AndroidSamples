package com.chenyangqi.roomword.sample.data

import androidx.annotation.WorkerThread
import com.chenyangqi.roomword.sample.dao.WordDao
import kotlinx.coroutines.flow.Flow

/**
 * @describe
 * @author chenyangqi
 * @time 2022/1/19 13:40
 */
class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}