package com.practice.callrecordingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.callrecordingapp.R
import com.practice.callrecordingapp.User
import com.practice.callrecordingapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        getData()
        clickListeners()
        setContentView(binding.root)
    }

    private fun getData() {
        val data = intent.getParcelableExtra<User>("user")
        setData(data)
    }

    private fun setData(data: User?) {
        binding.name.text = data?.name
        binding.designation.text = data?.designation
        binding.employeeId.text = "EmployeeId: ${data?.employeeId}"
        binding.phone.text = data?.contact
    }

    private fun clickListeners() {
        binding.tvLogin.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AlreadyLoginActivity::class.java
                )
            )
        }
    }
}