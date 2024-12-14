package ir.noori.domain

interface Repository {
    suspend fun getUsers(): Result<List<UserModel>> // Use Result to handle success/failure

}