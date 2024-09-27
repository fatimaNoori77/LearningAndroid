package ir.noori.learningandroid

data class UsersModel(
    val id: Int,
    val name: String,
    val username: String = "",
    val email: String = "",
    val phone: String ="",
    val website: String = ""
)
