package ir.noori.data

import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun fetchUsers(): List<UserDto>
}