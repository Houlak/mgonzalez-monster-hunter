package com.example.monsterhunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.monsterhunter.databinding.ActivityMainBinding
import com.example.monsterhunter.ui.ArmorsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(binding.fragmentContainer.id, ArmorsFragment())
                commit()
            }
        } else {
            supportFragmentManager.fragments.last()
        }
    }
}