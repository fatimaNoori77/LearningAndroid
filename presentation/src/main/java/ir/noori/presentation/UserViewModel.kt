package ir.noori.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.noori.domain.Repository
import ir.noori.domain.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _getUsers = MutableLiveData<List<UserModel>>()
    val getUsers: LiveData<List<UserModel>> get() = _getUsers

    fun fetchUsers() {
        viewModelScope.launch {
            val result = repository.getUsers()
            result.onSuccess { userList ->
                _getUsers.value = userList
            }.onFailure { exception ->
                Log.e("UserViewModel", "Failed to fetch users: ${exception.message}")
            }
        }
    }
}