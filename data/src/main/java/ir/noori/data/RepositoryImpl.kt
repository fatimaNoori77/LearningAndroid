package ir.noori.data

import ir.noori.domain.Repository
import ir.noori.domain.UserDto
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService // Or any other dependencies
) : Repository {
    override suspend fun getUsers(): Result<List<UserDto>> {
        return try {
            val users = apiService.fetchUsers()
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}