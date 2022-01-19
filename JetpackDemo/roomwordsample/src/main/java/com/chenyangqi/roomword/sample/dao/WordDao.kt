package com.chenyangqi.roomword.sample.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chenyangqi.roomword.sample.Word
import kotlinx.coroutines.flow.Flow

/**
 * @describe Word的数据库Dao
 * @author chenyangqi
 * @time 2022/1/19 12:04
 */
@Dao
interface WordDao {

    /**
     * 查找所有列
     */
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    /**
     * 插入列
     * OnConflictStrategy.IGNORE：策略将忽略与列表中的现有字词完全相同的新字词
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    /**
     * 删除word_table所有列
     */
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}