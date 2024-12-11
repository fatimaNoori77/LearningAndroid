package ir.noori.data

import ir.noori.domain.UserDto
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun fetchUsers(): List<UserDto>
}