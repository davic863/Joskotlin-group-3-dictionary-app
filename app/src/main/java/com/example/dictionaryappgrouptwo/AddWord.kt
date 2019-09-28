package com.example.dictionaryappgrouptwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dictionaryappgrouptwo.Helpers.DatabaseHelper
import com.example.dictionaryappgrouptwo.Model.Words



class AddWord : AppCompatActivity() {
/*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)





        val btAddword: Button = findViewById(R.id.bt_Add)
        val Title: EditText = findViewById(R.id.et_new_word)
        val  Meaning: EditText = findViewById(R.id.et_wrd_meaning)

        btAddword.setOnClickListener(View.OnClickListener {

            val title:String = Title.text.toString().trim()
            val meaning:String= Meaning.text.toString().trim()


            if (title.isEmpty()){
                Title.setError("empty word filed")


            } else if (meaning.isEmpty()){

                Meaning.setError("word has no meaning")

            }
            else{

                // create the Database helper instance to push your form data

                val databaseHelper = DatabaseHelper(this)

                if (!databaseHelper.checkWord(title)){
                    val word = Words(title = title, meaning = meaning)

                    databaseHelper.addWord(word)

                    /* databaseHelper.addUser(user)
*/
                    Toast.makeText(this,"word added  Successfully", Toast.LENGTH_LONG).show()

                    val toViewWords = Intent(this,MainActivity::class.java)
                    startActivity(toViewWords)
                    finish()
                } else{
                    Toast.makeText(this,"Word already exist", Toast.LENGTH_LONG).show()
                }
            }


        })





    }
}
