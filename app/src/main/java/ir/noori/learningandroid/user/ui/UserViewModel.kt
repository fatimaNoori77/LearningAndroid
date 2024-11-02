package ir.noori.learningandroid.user.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.noori.learningandroid.user.Repository
import ir.noori.learningandroid.user.data.entity.mapToUserModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getUsers = MutableLiveData<List<UserModel>>()
    val getUsers: LiveData<List<UserModel>> get() = _getUsers
    fun fetchUsers(){
        repository.getUsers {
                _getUsers.value = it.map { it.mapToUserModel() } ?: emptyList()
        }
    }
}