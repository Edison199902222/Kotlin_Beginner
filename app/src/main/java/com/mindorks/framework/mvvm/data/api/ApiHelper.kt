package com.mindorks.framework.mvvm.data.api
// 给出users
class ApiHelper(private val apiService: ApiService) {

   fun getUsers() = apiService.getUsers()

}