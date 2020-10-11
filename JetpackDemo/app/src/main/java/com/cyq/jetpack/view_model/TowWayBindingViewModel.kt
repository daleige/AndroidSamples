package com.cyq.jetpack.view_model

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cyq.jetpack.BR
import com.cyq.jetpack.model.LoginModel

/**
 * @author : ChenYangQi
 * date   : 2020/10/11 10:52
 * desc   : BaseObservable实现双向绑定的viewModel
 */
class TowWayBindingViewModel : BaseObservable() {
    private var loginModel: LoginModel = LoginModel("张三", "123456")

    @Bindable
    fun getUserName(): String {
        return loginModel.userName
    }

    @Bindable
    fun getPassword(): String {
        return loginModel.password
    }

    fun setUserName(username: String) {
        if (!TextUtils.isEmpty(username) && username != loginModel.userName) {
            loginModel.userName = username
            notifyPropertyChanged(BR.userName)
        }
    }

    fun setPassword(password: String) {
        if (!TextUtils.isEmpty(password) && password != loginModel.password) {
            loginModel.password = password
            notifyPropertyChanged(BR.password)
        }
    }

}