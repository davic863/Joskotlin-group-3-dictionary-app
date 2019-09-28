package com.example.dictionaryappgrouptwo

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryappgrouptwo.Helpers.DatabaseHelper
import com.example.dictionaryappgrouptwo.Helpers.wordRecyclerAdapter
import com.example.dictionaryappgrouptwo.Model.Words
import kotlinx.android.synthetic.main.activity_add_word.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var allWordrecycler: RecyclerView
    private lateinit var listView        : MutableList<Words>
    private lateinit var recyclerAdapter : wordRecyclerAdapter
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        allWordrecycler = findViewById<View>(R.id.all_words_recycler)as RecyclerView
        listView         = ArrayList()
        recyclerAdapter  = wordRecyclerAdapter(listView,this)

        val wlayoutManager = LinearLayoutManager(this)
        allWordrecycler.layoutManager = wlayoutManager

        allWordrecycler.setHasFixedSize(true)

        //it links your recycler adapter class to the recycler view on your xml file

        allWordrecycler.adapter = recyclerAdapter

        databaseHelper = DatabaseHelper(this)

        GetDataFromSQLite().execute()



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                this.startActivity(Intent(this,AddWord::class.java))
                return true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }


    // this class is to fetch all user record from SQLIte without lagging

    inner class GetDataFromSQLite: AsyncTask<Void, Void, List<Words>>() {

        override fun doInBackground(vararg p0: Void?): List<Words> {

            return databaseHelper.fetchWords()

        }

        override fun onPostExecute(result: List<Words>?) {
            super.onPostExecute(result)
            listView.clear()

            listView.addAll(result!!)
        }
    }

}
