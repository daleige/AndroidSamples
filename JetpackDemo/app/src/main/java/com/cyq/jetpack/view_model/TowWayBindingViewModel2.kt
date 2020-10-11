package com.cyq.jetpack.view_model

import androidx.databinding.ObservableField
import com.cyq.jetpack.model.LoginModel

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 11:26
 * desc   :ObservableFieId实现双向绑定
 */
class TowWayBindingViewModel2() {
    private val loginModelObservableField:ObservableField<LoginModel> = ObservableField()
    private val loginModel = LoginModel("李四", "666666")

    init {
        loginModelObservableField.set(loginModel)
    }

    fun getUserName(): String? {
        return loginModelObservableField.get()?.userName
    }

    fun getPassword(): String? {
        return loginModelObservableField.get()?.password
    }

    fun setUserName(userName: String) {
        loginModelObservableField.get()?.userName = userName
    }

    fun setPassword(password: String) {
        loginModelObservableField.get()?.password = password
    }

}