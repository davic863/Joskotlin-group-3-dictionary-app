package com.example.dictionaryappgrouptwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.dictionaryappgrouptwo.Helpers.DatabaseHelper
import com.example.dictionaryappgrouptwo.Model.Words

class UpdateWords : AppCompatActivity() {


        private lateinit var et_title: EditText
        private lateinit var et_meaning: EditText

        private lateinit var update_title:String
        private lateinit var update_meaning:String



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_update_words)

            val id = intent.getIntExtra("id",-1)
            val title = intent.getStringExtra("title")
            val meaning = intent.getStringExtra("meaning")


            et_title = findViewById(R.id.Uw_new_word)
            et_meaning = findViewById(R.id.UW_wrd_meaning)



            et_title.setText(title)
            et_meaning.setText(meaning)


            val update_bt: Button = findViewById(R.id.bt_Update)

            update_bt.setOnClickListener(View.OnClickListener {


                update_title = et_title.text.toString().trim()
                update_meaning = et_meaning.text.toString().trim()





                val db_helper = DatabaseHelper(this)


                val words = Words(id = id,title = update_title,meaning = update_meaning)


                db_helper.updateWord(words)


                val toUpdate = Intent(this, MainActivity::class.java)

                toUpdate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                toUpdate.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                toUpdate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(toUpdate)
                finish()

            })

    }
}

