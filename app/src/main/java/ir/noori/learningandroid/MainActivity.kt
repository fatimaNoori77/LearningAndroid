package ir.noori.learningandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.noori.learningandroid.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fetchUsers()

    }

    private fun fetchUsers(){
        RequestHelper.builder("https://jsonplaceholder.typicode.com/users")
            .listener(object : RequestHelper.Callback() {
                override fun onResponse(reCall: Runnable?, vararg args: Any?) {
                    runOnUiThread {
                        try {
                            val users = ArrayList<UsersModel>()
                            val response = JSONArray(args[0].toString())
                            for(i in 0 until response.length()){
                                val userObj = JSONObject(response.getJSONObject(i).toString())
                                val user =  UsersModel(
                                    id = userObj.getInt("id"),
                                    name =  userObj.getString("name"),
                                    email = userObj.getString("email")
                                )
                                users.add(user)
                            }

                            fillList(users)

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(reCall: Runnable?, e: java.lang.Exception?) {

                }
            }
            )
            .get()
    }

    private fun fillList(users: ArrayList<UsersModel>){
        val adapter = UsersAdapter(users)
        binding.users.adapter = adapter
    }

}