package ir.noori.domain

interface Repository {
    suspend fun getUsers(): Result<List<UserDto>> // Use Result to handle success/failure

}