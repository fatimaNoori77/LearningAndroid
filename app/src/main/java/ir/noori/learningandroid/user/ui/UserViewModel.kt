package ir.noori.learningandroid.user.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.noori.learningandroid.user.Repository
import ir.noori.learningandroid.user.data.entity.UsersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repository: Repository = Repository()) : ViewModel() {

    val users = MutableLiveData<List<UsersModel>>()
    val errorMessage = MutableLiveData<String>()
    fun fetchUsers(){
        val response = repository.fetchUsers()
        response.enqueue(object : Callback<List<UsersModel>>{
            override fun onResponse(p0: Call<List<UsersModel>>, p1: Response<List<UsersModel>>) {
               users.postValue(p1.body())
            }

            override fun onFailure(p0: Call<List<UsersModel>>, p1: Throwable) {
                errorMessage.postValue(p1.message)
            }

        })
    }
}