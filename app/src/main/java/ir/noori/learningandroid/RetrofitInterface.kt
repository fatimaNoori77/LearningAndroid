package ir.noori.learningandroid

import ir.noori.learningandroid.user.data.entity.UsersModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("users")
    fun fetchUsers(): Call<List<UsersModel>>
}