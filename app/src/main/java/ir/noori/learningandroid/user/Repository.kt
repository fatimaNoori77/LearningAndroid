package ir.noori.learningandroid.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ir.noori.learningandroid.ApiService
import ir.noori.learningandroid.user.data.entity.UserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    fun getUsers(callBack:(List<UserDto>)->Unit){
        val errorMessage = MutableLiveData<String>()
        apiService.fetchUsers().enqueue(object : Callback<List<UserDto>> {
            override fun onResponse(p0: Call<List<UserDto>>, p1: Response<List<UserDto>>) {
                if(p1.body() == null){
                    Log.i("TAG", "onResponse: isNulllllllll")
                }else{
                   callBack(p1.body()!!)
                }
            }

            override fun onFailure(p0: Call<List<UserDto>>, p1: Throwable) {
                errorMessage.postValue(p1.message)
            }
        })
    }
}