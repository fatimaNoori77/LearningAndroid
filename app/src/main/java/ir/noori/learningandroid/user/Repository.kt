package ir.noori.learningandroid.user

import ir.noori.learningandroid.RetrofitInstance
import javax.inject.Inject

class Repository @Inject constructor() {
    fun fetchUsers()= RetrofitInstance.retrofit.fetchUsers()
}