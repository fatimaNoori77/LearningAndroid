package ir.noori.data

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