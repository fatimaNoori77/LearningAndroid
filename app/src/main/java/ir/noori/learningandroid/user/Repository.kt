package ir.noori.learningandroid.user

import ir.noori.learningandroid.MyApplication
import ir.noori.learningandroid.utils.RequestHelper
import ir.noori.learningandroid.user.data.entity.UsersModel
import org.json.JSONArray
import org.json.JSONObject

class Repository {

    fun fetchUsers(onResponse: (ArrayList<UsersModel>?) -> Unit) {
        RequestHelper.builder("https://jsonplaceholder.typicode.com/users")
            .listener(object : RequestHelper.Callback() {
                override fun onResponse(reCall: Runnable?, vararg args: Any?) {
                    MyApplication.handler.post{
                        try {
                            val users = ArrayList<UsersModel>()
                            val response = JSONArray(args[0].toString())
                            for (i in 0 until response.length()) {
                                val userObj = JSONObject(response.getJSONObject(i).toString())
                                val user = UsersModel(
                                    id = userObj.getInt("id"),
                                    name = userObj.getString("name"),
                                    email = userObj.getString("email")
                                )
                                users.add(user)
                            }

                            onResponse(users)

                        } catch (e: Exception) {
                            e.printStackTrace()
                            onResponse(null)
                        }
                    }
                }

                override fun onFailure(reCall: Runnable?, e: java.lang.Exception?) {
                    MyApplication.handler.post {
                        onResponse(null)
                    }
                }
            }
            )
            .get()
    }

}