package ir.noori.learningandroid.user.ui

data class UserModel(
    var id: Int,
    var name: String,
    var username: String = "",
    var email: String = "",
    var phone: String ="",
    var website: String = ""
)
