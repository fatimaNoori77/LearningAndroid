package ir.noori.learningandroid.user.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.noori.learningandroid.user.Repository
import ir.noori.learningandroid.user.data.entity.UsersModel

class UserViewModel(private val repository: Repository = Repository()) : ViewModel() {

    private val _users = MutableLiveData<ArrayList<UsersModel>>()
    val users: LiveData<ArrayList<UsersModel>> get() = _users

    fun fetchHistoryList(){
        repository.fetchUsers {
            _users.value = it
        }
    }


}