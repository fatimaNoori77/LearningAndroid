package ir.noori.learningandroid

import ir.noori.learningandroid.user.Repository
import ir.noori.learningandroid.user.data.entity.UserDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(apiService)
    }

    @Test
    fun getUsers_returnsSuccess_whenApiServiceFetchesUsers() = runBlocking {
        // Arrange
        val userDtoList = listOf(UserDto(1, "John"), UserDto(2, "Jane"))
        Mockito.`when`(apiService.fetchUsers()).thenReturn(userDtoList)

        // Act
        val result = repository.getUsers()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(userDtoList, result.getOrNull())
    }

    @Test
    fun getUsers_returns_failure_when_ApiService_throws_exception() = runBlocking {
        // Arrange
        val exception = RuntimeException("Network error")
        Mockito.`when`(apiService.fetchUsers()).thenThrow(exception)

        // Act
        val result = repository.getUsers()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
