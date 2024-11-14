package ir.noori.learningandroid.user

import ir.noori.learningandroid.ApiService
import ir.noori.learningandroid.user.data.entity.UserDto
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers(): Result<List<UserDto>>{
        return try {
            val response = apiService.fetchUsers()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}