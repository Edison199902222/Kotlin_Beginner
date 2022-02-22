package com.mindorks.framework.mvvm.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvvm.data.model.User
import com.mindorks.framework.mvvm.data.repository.MainRepository
import com.mindorks.framework.mvvm.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
// ui部分
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    // Step 1: Creating an instance of LiveData 开始使用livedata，订阅某些data
    private val users = MutableLiveData<Resource<List<User>>>()
    // 可以一瞬间全取消的 compositeDisposable
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }
    // 设置livedata 和 compositeDisposable
    private fun fetchUsers() {
        // step 2: Set the data in LiveData，集中关注resource的data
        users.postValue(Resource.loading(null))
        // 放进去compositeDisposable
        compositeDisposable.add(
            mainRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                    // Subscribes to a Single and provides callbacks to handle the item it emits or any error notification it issues.
                .subscribe({ userList ->
                    // step 2: Set the data in LiveData
                    users.postValue(Resource.success(userList)) // lambda 表达式，箭头前面是参数，后面是函数体，传入lambda函数给substribe用
                }, { throwable ->
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }
    // 取消所有的api call
    override fun onCleared() {
        super.onCleared()
        // 一瞬间取消
        compositeDisposable.dispose()
    }
    // Step 3: Returning LiveData
    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }

}