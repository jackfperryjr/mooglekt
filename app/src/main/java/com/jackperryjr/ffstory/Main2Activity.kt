package com.jackperryjr.ffstory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var prevButton = findViewById(R.id.prev_button) as Button

        prevButton.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent)
        })

        var nextButton = findViewById(R.id.next_button) as Button

        nextButton.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(view.context, Main3Activity::class.java)
            view.context.startActivity(intent)
        })
    }
}
