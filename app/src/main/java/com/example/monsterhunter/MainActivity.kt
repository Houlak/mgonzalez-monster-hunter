package com.example.monsterhunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.monsterhunter.data.models.Armor
import com.example.monsterhunter.databinding.ActivityMainBinding
import com.example.monsterhunter.ui.ArmorDetailsBottomSheet
import com.example.monsterhunter.ui.ArmorsFragment

class MainActivity : AppCompatActivity(), ArmorsFragment.OnFragmentInteractionListener {

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

    override fun showArmorDetails(armor: Armor) {
        ArmorDetailsBottomSheet.newInstance(armor).show(
            supportFragmentManager, ArmorDetailsBottomSheet.TAG
        )
    }
}