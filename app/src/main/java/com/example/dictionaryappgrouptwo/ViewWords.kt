package com.example.dictionaryappgrouptwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.dictionaryappgrouptwo.Helpers.DatabaseHelper
import com.example.dictionaryappgrouptwo.Model.Words

class ViewWords : AppCompatActivity() {


        private lateinit var tv_title: TextView
        private lateinit var tv_meaning: TextView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_view_words)

            val id = intent.getIntExtra("id", -1)
            val title = intent.getStringExtra("title")
            val meaning = intent.getStringExtra("meaning")


            tv_title = findViewById(R.id.VW_title)
            tv_meaning = findViewById(R.id.VW_meaning)



            tv_title.setText(title)
            tv_meaning.setText(meaning)


            val update_bt: Button = findViewById(R.id.bt_VW_edit)
            val delete_bt: Button = findViewById(R.id.bt_VW_delete)

            update_bt.setOnClickListener(View.OnClickListener {
                val toUpdate = Intent(this, UpdateWords::class.java)

                //sends id, name, email, address and password to the next page
                toUpdate.putExtra("id", id)
                toUpdate.putExtra("title", title)
                toUpdate.putExtra("meaning", meaning)


                startActivity(toUpdate)

            })



            delete_bt.setOnClickListener(View.OnClickListener {
                val db_helper = DatabaseHelper(this)
                val words = Words(id = id, title = "", meaning = "")
                db_helper.deleteWord(words)

                val toMain = Intent(this, MainActivity::class.java)
                toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(toMain)
                finish()
                Toast.makeText(this, "Word Deleted successfully", Toast.LENGTH_LONG).show()

            })


        }


    }
