package com.mindorks.framework.mvvm.utils

// data class means this class only contains data
data class Resource<out T> (val status: Status, val data: T?, val message: String?){
    // 等效于static
    companion object {
        // 因为在内部需要return resource object，所以需要out 来修饰进行covariance，不然无法return
        // 代表可以返回resource的子类
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}