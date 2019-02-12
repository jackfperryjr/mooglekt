package com.jackperryjr.mooglekt

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.content.Context
import android.widget.ArrayAdapter

import com.squareup.picasso.Picasso

import org.json.JSONObject

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setTitle()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        val character = JSONObject(getIntent().getStringExtra("character"))

        val characterName = findViewById<TextView>(R.id.character_name)
        characterName.text = character.optString("name")

        val characterAge = findViewById<TextView>(R.id.character_age)
        characterAge.text = character.optString("age")

        val characterGender = findViewById<TextView>(R.id.character_gender)
        characterGender.text = character.optString("gender")

        val characterRace = findViewById<TextView>(R.id.character_race)
        characterRace.text = character.optString("race")

        val characterJob = findViewById<TextView>(R.id.character_job)
        characterJob.text = character.optString("job")

        val characterImageUrl = character.optString("picture")
        val characterImage = findViewById<ImageView>(R.id.character_avatar)

        Picasso.with(applicationContext).load(characterImageUrl).into(characterImage)

        var message = findViewById<EditText>(R.id.compose_message)
        var send = findViewById<Button>(R.id.send_button)

        var messages = findViewById<ListView>(R.id.messages)
        val listMessages = ArrayList<String>()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listMessages
        )

        messages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL)
        messages.setAdapter(adapter)

        var back = findViewById<Button>(R.id.back_button)
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        message.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                listMessages.add("You: " + message.getText())
                message.getText().clear()
                messages.invalidateViews()
                closeKeyboard()
                val handler = Handler()
                var responseTime = responseDiceRoll()
                var responseIndex = diceRoll()
                var characterMatch = character.optString("name").split(" ")
                val responseMessages = ArrayList<String>()
                responseMessages.add(characterMatch[0] + ": Hey there!")
                responseMessages.add(characterMatch[0] + ": How's the weather?")
                responseMessages.add(characterMatch[0] + ": What's your favorite game?")
                responseMessages.add(characterMatch[0] + ": Lovely.")
                responseMessages.add(characterMatch[0] + ": Yeah!")
                responseMessages.add(characterMatch[0] + ": Nope.")
                responseMessages.add(characterMatch[0] + ": OMG")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": LOL")
                responseMessages.add(characterMatch[0] + ": What?!")
                responseMessages.add(characterMatch[0] + ": You're so funny.")
                responseMessages.add(characterMatch[0] + ": ...")
                responseMessages.add(characterMatch[0] + ": I love what I do.")
                responseMessages.add(characterMatch[0] + ": Final Fantasy is so cool.")
                responseMessages.add(characterMatch[0] + ": Final Fantasy is so cool.")
                handler.postDelayed(Runnable {
                    listMessages.add(responseMessages[responseIndex])
                    messages.invalidateViews()
                }, responseTime)
                return@OnKeyListener true
            }
            false
        })

        send.setOnClickListener {
            listMessages.add("You: " + message.getText())
            message.getText().clear()
            messages.invalidateViews()
            closeKeyboard()
            val handler = Handler()
            var responseTime = responseDiceRoll()
            var responseIndex = diceRoll()
            var characterMatch = character.optString("name").split(" ")
            val responseMessages = ArrayList<String>()
            responseMessages.add(characterMatch[0] + ": Hey there!")
            responseMessages.add(characterMatch[0] + ": How's the weather?")
            responseMessages.add(characterMatch[0] + ": What's your favorite game?")
            responseMessages.add(characterMatch[0] + ": Lovely.")
            responseMessages.add(characterMatch[0] + ": Yeah!")
            responseMessages.add(characterMatch[0] + ": Nope.")
            responseMessages.add(characterMatch[0] + ": OMG")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": LOL")
            responseMessages.add(characterMatch[0] + ": What?!")
            responseMessages.add(characterMatch[0] + ": You're so funny.")
            responseMessages.add(characterMatch[0] + ": ...")
            responseMessages.add(characterMatch[0] + ": I love what I do.")
            responseMessages.add(characterMatch[0] + ": Final Fantasy is so cool.")
            responseMessages.add(characterMatch[0] + ": Final Fantasy is so cool.")
            handler.postDelayed(Runnable {
                listMessages.add(responseMessages[responseIndex])
                messages.invalidateViews()
            }, responseTime)
        }
    }

    private fun closeKeyboard() {
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            this.getCurrentFocus().getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun diceRoll(): Int {
        return (0..20).random()
    }

    private fun responseDiceRoll(): Long {
        return (2000..9000).random().toLong()
    }

    private fun setTitle() { //Used to color the title.
        var titleBar = SpannableString("Moogle Matchmaker")
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(66,133,244)), 0, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(204,0,0)), 1, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(255,187,51)), 2, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(66,133,244)), 3, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(0,126,51)), 4, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(204,0,0)), 5, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleBar.setSpan(ForegroundColorSpan(Color.rgb(255,255,255)), 6, titleBar.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setTitle(titleBar)
    }
}
