package ir.noori.learningandroid.user.data.entity

data class UsersModel(
    var id: Int,
    var name: String,
    var username: String = "",
    var email: String = "",
    var phone: String ="",
    var website: String = ""
)
