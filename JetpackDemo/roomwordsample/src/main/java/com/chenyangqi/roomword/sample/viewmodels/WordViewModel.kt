package com.chenyangqi.roomword.sample.viewmodels

import androidx.lifecycle.*
import com.chenyangqi.roomword.sample.data.Word
import com.chenyangqi.roomword.sample.data.WordRepository
import kotlinx.coroutines.launch

/**
 * @describe
 * @author chenyangqi
 * @time 2022/1/19 14:16
 */
class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) {
        viewModelScope.launch {
            repository.insert(word)
        }
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}