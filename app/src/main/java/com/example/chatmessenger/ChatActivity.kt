package com.example.chatmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val username = intent.getStringExtra("DataUser")
        tvHelloFromChat.text = "${tvHelloFromChat.text}\n$username"
    }
}