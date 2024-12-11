package ir.noori.learningandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ir.noori.domain.UserModel
import ir.noori.learningandroid.databinding.ActivityMainBinding
import ir.noori.presentation.UserViewModel
import ir.noori.presentation.UsersAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

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

        viewModel.fetchUsers()

        // It's good to display a loader until the response is received.
        viewModel.getUsers.observe(this){
            fillList(it)
        }

//        viewModel.errorMessage.observe(this){
//            // I should handle errors here by showing a dialog or something similar...
//        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    private fun fillList(users: List<UserModel>){
        // I should handle empty list here later
        val adapter = UsersAdapter(users)
        binding.users.adapter = adapter
    }

}