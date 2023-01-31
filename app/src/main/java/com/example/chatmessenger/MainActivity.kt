package com.example.chatmessenger

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.chatmessenger.classes.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.usernameRegisterEditText
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.Delay
import java.util.Timer


class MainActivity : AppCompatActivity() {
    val users = arrayListOf<User>(
        User("Username1", "Qwerty1", "username1@gmail.com"),
        User("Username2", "Qwerty2", "username2@gmail.com"),
        User("Username3", "Qwerty3", "username3@gmail.com"),
        User("Username4", "Qwerty4", "username4@gmail.com"),
        User("Username5", "Qwerty5", "username5@gmail.com"),
        User("Username6", "Qwerty6", "username6@gmail.com"),
        User("Username7", "Qwerty7", "username7@gmail.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onButtonSignUpClick(view: View) {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }

    fun onButtonSignInClick(view: View) {
        val username = usernameRegisterEditText.text.toString()
        val password = passwordEditText.text.toString()
        if (User.isUserExist(username, password, users)) {
            startActivity(Intent(this, ChatActivity::class.java))
        } else {
            usernameRegisterEditText.text = null
            passwordEditText.text = null
            messageShow(layoutMessage_1)
        }
    }

    private fun messageShow(view: View) {
        view.visibility = View.VISIBLE
        buttonSignIn.isEnabled = false
        buttonSignUp.isEnabled = false
        var count = 0
        object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                count++
                if (count == 3) {
                    layoutMessage_1.animate().alpha(0.0f).duration = 3500
                }
            }
            override fun onFinish() {
                view.visibility = View.GONE
                view.alpha = 1.0f
                buttonSignIn.isEnabled = true
                buttonSignUp.isEnabled = true
            }
        }.start()
    }


}