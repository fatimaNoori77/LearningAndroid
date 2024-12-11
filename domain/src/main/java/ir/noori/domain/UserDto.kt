package ir.noori.domain

data class UserDto(
    var id: Int,
    var name: String,
    var username: String = "",
    var email: String = "",
    var phone: String = "",
    var website: String = ""
)

fun UserDto.mapToUserModel() = UserModel(
    id = this.id,
    name = this.name,
    username = this.username,
    email = this.email,
    phone = this.phone,
    website = this.website
)