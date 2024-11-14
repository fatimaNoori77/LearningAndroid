package ir.noori.learningandroid

import ir.noori.learningandroid.user.data.entity.UserDto
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun fetchUsers(): List<UserDto>
}