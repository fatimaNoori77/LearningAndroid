package ir.noori.learningandroid.user

import ir.noori.learningandroid.RetrofitInstance

class Repository {
    fun fetchUsers()= RetrofitInstance.retrofit.fetchUsers()
}