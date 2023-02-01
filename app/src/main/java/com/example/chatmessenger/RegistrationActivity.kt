package com.example.chatmessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.chatmessenger.classes.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.usernameRegisterEditText
import kotlinx.android.synthetic.main.activity_registration.*
import java.io.Serializable


@Suppress("DEPRECATION")
class RegistrationActivity : AppCompatActivity() {
    var users: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        users = intent.getSerializableExtra("DataUsers") as ArrayList<User>

        // for focus (change background fields)
        usernameRegisterEditText.setOnFocusChangeListener { _, _ ->
            usernameRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_grey)
        }
        passwordRegisterEditText.setOnFocusChangeListener { _, _ ->
            passwordRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_grey)
        }
        passwordRegisterConfirmEditText.setOnFocusChangeListener { _, _ ->
            passwordRegisterConfirmInputLayout.setBoxBackgroundColorResource(R.color.light_grey)
        }
        emailRegisterConfirmEditText.setOnFocusChangeListener { _, _ ->
            emailRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_grey)
        }
    }

    fun onClickButtonRegister(view: View) {
        val username = usernameRegisterEditText.text.toString()
        val password = passwordRegisterEditText.text.toString()
        val confirmPassword = passwordRegisterConfirmEditText.text.toString()
        val email = emailRegisterConfirmEditText.text.toString()

        if (checkFieldsOnNullOrEmpty()) return
        val user = User(username, password, email)
        when (User.registerUser(user, confirmPassword, users)) {
            1 -> {
                clearFields()
                showErrorMessage(getString(R.string.textMessage5))
            }
            2 -> {
                clearFields()
                showErrorMessage(getString(R.string.textMessage2))
            }
            3 -> {
                clearFields()
                showErrorMessage(getString(R.string.textMessage3))
            }
            4 -> {
                clearFields()
                showErrorMessage(getString(R.string.textMessage4))
            }
            5 -> {
                startActivity(Intent(this, ChatActivity::class.java))
            }
        }
    }

    private fun checkFieldsOnNullOrEmpty(): Boolean {
        var isFieldNullOrEmpty = false
        if (usernameRegisterEditText.text.isNullOrEmpty()) {
            usernameRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_red)
            isFieldNullOrEmpty = true
        }
        if (passwordRegisterEditText.text.isNullOrEmpty()) {
            passwordRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_red)
            isFieldNullOrEmpty = true
        }
        if (passwordRegisterConfirmEditText.text.isNullOrEmpty()) {
            passwordRegisterConfirmInputLayout.setBoxBackgroundColorResource(R.color.light_red)
            isFieldNullOrEmpty = true
        }
        if (emailRegisterConfirmEditText.text.isNullOrEmpty()) {
            emailRegisterInputLayout.setBoxBackgroundColorResource(R.color.light_red)
            isFieldNullOrEmpty = true
        }
        return isFieldNullOrEmpty
    }

    private fun clearFields() {
        usernameRegisterEditText.text = null
        passwordRegisterEditText.text = null
        passwordRegisterConfirmEditText.text = null
        emailRegisterConfirmEditText.text = null
    }

    private fun showErrorMessage(message: String) {
        twRegistration.visibility = View.GONE
        buttonRegister.isEnabled = false
        message_2.text = message
        message_2.visibility = View.VISIBLE
        var count = 0
        object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                count++
                if (count == 3) {
                    message_2.animate().alpha(0.0f).duration = 1000
                }
            }
            override fun onFinish() {
                message_2.visibility = View.GONE
                message_2.alpha = 1.0f
                twRegistration.visibility = View.VISIBLE
                buttonRegister.isEnabled = true
            }
        }.start()
    }


}