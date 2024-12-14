package ir.noori.data

import ir.noori.domain.Repository
import ir.noori.domain.UserModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun getUsers(): Result<List<UserModel>> {
        return try {
            val users = apiService.fetchUsers() // Fetch data (UserDto)
            Result.success(users.map { it.mapToUserModel() }) // Map to UserModel
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}