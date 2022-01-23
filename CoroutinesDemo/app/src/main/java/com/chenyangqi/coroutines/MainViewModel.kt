package com.chenyangqi.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chenyangqi.coroutines.bean.DataResponseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author : ChenYangQi
 * date   : 2022/1/23 17:38
 * desc   :
 */
class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun updateTaps(): Result<DataResponseBean>? {
        var result: Result<DataResponseBean>? = null
        viewModelScope.launch(Dispatchers.IO) {
            result = dataRepository.requestData()
        }
        return result
    }
}