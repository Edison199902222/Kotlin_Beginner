package com.mindorks.framework.mvvm.data.api

import com.mindorks.framework.mvvm.data.model.User
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
// The Single class implements the Reactive Pattern for a single value response.
class ApiServiceImpl : ApiService {
    override fun getUsers(): Single<List<User>> {
        // User::class.java 从java class 转换成kotlin
        println("this is single list " + Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users").build().getObjectListSingle(User::class.java))
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users").build().getObjectListSingle(User::class.java)
    }

}