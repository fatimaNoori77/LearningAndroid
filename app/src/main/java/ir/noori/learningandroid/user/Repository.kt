package ir.noori.learningandroid.user

import ir.noori.learningandroid.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    fun fetchUsers()= apiService.fetchUsers()
}