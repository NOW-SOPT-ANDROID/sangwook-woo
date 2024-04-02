package org.sopt.main.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.main.const.IntentKey.USER_KEY
import org.sopt.main.databinding.ActivityMainBinding
import org.sopt.main.model.User
import org.sopt.ui.intent.getParcelable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val user by lazy {
        intent.getParcelable(USER_KEY, User::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            tvMainName.text = user?.name
            tvMainRegisterid.text = user?.id
            tvMainRegisterpw.text = user?.pw
            tvMainRegisterhobby.text = user?.hobby
        }
    }
}