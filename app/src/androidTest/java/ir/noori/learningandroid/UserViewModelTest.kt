package ir.noori.learningandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ir.noori.learningandroid.user.Repository
import ir.noori.learningandroid.user.data.entity.UserDto
import ir.noori.learningandroid.user.data.entity.mapToUserModel
import ir.noori.learningandroid.user.ui.UserViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: UserViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule() // Custom rule for testing coroutines

    @Before
    fun setUp() {
        viewModel = UserViewModel(repository)
    }

    @Test
    fun fetchUsers_updates_LiveData_with_user_list_on_success() = runTest {
        // Arrange
        val userDtoList = listOf(UserDto(1, "John"), UserDto(2, "Jane"))
        val userModelList = userDtoList.map { it.mapToUserModel() }

        Mockito.`when`(repository.getUsers()).thenReturn(Result.success(userDtoList))

        // Act
        viewModel.fetchUsers()

        // Assert
        val observedValue = viewModel.getUsers.getOrAwaitValue()
        assertEquals(userModelList, observedValue)
    }

    @Test
    fun fetchUsers_does_not_update_LiveData_on_failure() = runTest {
        // Arrange
        val exception = RuntimeException("Network error")
        Mockito.`when`(repository.getUsers()).thenReturn(Result.failure(exception))

        // Act
        viewModel.fetchUsers()

        // Assert
        val observedValue = viewModel.getUsers.getOrAwaitValueOrNull()
        assertNull(observedValue)
    }

    fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return data ?: throw KotlinNullPointerException("No value was observed!")
    }

    fun <T> LiveData<T>.getOrAwaitValueOrNull(): T? {
        return try {
            getOrAwaitValue()
        } catch (e: KotlinNullPointerException) {
            null
        }
    }

}
