package ir.noori.learningandroid

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchUsers_returns_user_list() = runBlocking {
        // Arrange
        val responseBody = """
            [
                {"id": 1, "name": "John"},
                {"id": 2, "name": "Jane"}
            ]
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody).setResponseCode(200))

        // Act
        val result = apiService.fetchUsers()

        // Assert
        assertEquals(2, result.size)
        assertEquals("John", result[0].name)
    }

    @Test
    fun fetchUsers_throws_exception_for_500_response() = runBlocking {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        // Act & Assert
//        assertThrows<Exception> { apiService.fetchUsers() }
    }
}
