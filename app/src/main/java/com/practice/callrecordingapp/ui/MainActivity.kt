package com.practice.callrecordingapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.callrecordingapp.User
import com.practice.callrecordingapp.databinding.ActivityMainBinding
import com.practice.callrecordingapp.model.Credential
import com.practice.callrecordingapp.networking.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initVaribles()
        clickListeners()
        setContentView(binding.root)
    }

    private fun initVaribles() {
        context = this
    }

    private fun clickListeners() {
        val apiService: ApiInterface = ApiInterface.create()
        binding.btnLogin.setOnClickListener {
            val name = binding.etUsername.text.toString();
            val password = binding.etPassword.text.toString()
            if (name != null && name.length > 3) {
                if (password != null && password.length > 5) {
                    var credential = Credential(password, username = name)
                    val apiInterface = apiService.loginUser(credential)
                    binding.progressBar.visibility = View.VISIBLE
                    apiInterface.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            binding.progressBar.visibility = View.GONE
                            if (response.isSuccessful) {
                                var user = response.body()
                                val intent = Intent(context, ProfileActivity::class.java)
                                user?.name = "Rizwan Haider"
                                user?.designation = "Sale Exective"
                                user?.employeeId = "0001"
                                user?.contact = "+9897567576"
                                intent.putExtra("user", user)
                                startActivity(intent)
                            }

                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            binding.progressBar.visibility = View.GONE
                            Log.e(TAG, "onFailure message= ${t.message}")
                        }


                    })
                } else {
                    Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(this, "User name is required", Toast.LENGTH_LONG).show()
            }
        }
    }
}