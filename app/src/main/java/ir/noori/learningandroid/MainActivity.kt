package ir.noori.learningandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ir.noori.learningandroid.databinding.ActivityMainBinding
import ir.noori.learningandroid.user.data.entity.UsersModel
import ir.noori.learningandroid.user.ui.UserViewModel
import ir.noori.learningandroid.user.ui.UsersAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // It's good to display a loader until the response is received.
        viewModel.fetchUsers()

        viewModel.users.observe(this){
            fillList(it as ArrayList<UsersModel>)
        }
        viewModel.errorMessage.observe(this){
            // I should handle errors here by showing a dialog or something similar...
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    private fun fillList(users: ArrayList<UsersModel>){
        // I should handle empty list here later
        val adapter = UsersAdapter(users)
        binding.users.adapter = adapter
    }

}