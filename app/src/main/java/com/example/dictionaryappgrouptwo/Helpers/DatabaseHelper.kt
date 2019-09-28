package com.example.dictionaryappgrouptwo.Helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dictionaryappgrouptwo.Model.Words

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {




    /*private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_USER_NAME+ " TEXT,"
            + COLUMN_USER_EMAIL+ " TEXT,"+ COLUMN_USER_PASSWORD+ " TEXT," + COLUMN_USER_ADDRESS+ " TEXT" +")"
            )

*/
    //Create a table that will perform our SQL Query

    private val CREATE_WORD_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_WORD_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_WORD_TITLE+ " TEXT,"
            + COLUMN_WORD_MEANING+ " TEXT" +")"
            )




    override fun onCreate(db:SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CREATE_WORD_TABLE)
        }
    }

    override fun onUpgrade(p0:SQLiteDatabase?, p1: Int, p2: Int) {

        if (p0 != null) {
            p0.execSQL(DROP_WORD_TABLE)
        }
        onCreate(p0)

    }
    fun addWord(words: Words){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_WORD_TITLE,words.title)
        values.put(COLUMN_WORD_MEANING,words.meaning)
        db.insert(TABLE_NAME, null,values)
        db.close()

    }

    fun checkWord(title:String):Boolean{
        //it specifies the array of columns to fetch
        val columns :Array<String> = arrayOf(COLUMN_WORD_ID)
        val db :SQLiteDatabase = readableDatabase

        // write the selection criteria

        val selecTion = "$COLUMN_WORD_TITLE = ?"

        // selection Arguments(the code that will perform the search)

        val selectionargs = arrayOf(title)

        val cursor = db.query(TABLE_NAME,
            columns,
            selecTion,
            selectionargs,
            null,
            null,
            null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount>0){
            return true
        }
        return false
    }

    fun deleteWord(words: Words){
        val db = this.writableDatabase
        //delete user records by id
        db.delete(TABLE_NAME,"$COLUMN_WORD_ID = ?",
            arrayOf(words.id.toString()))
    }
    fun updateWord(words: Words){

        val db = this.writableDatabase

        val values = ContentValues()

        values.put(COLUMN_WORD_TITLE,words.title)
        values.put(COLUMN_WORD_MEANING,words.meaning)

        db.update(
            TABLE_NAME, values,"$COLUMN_WORD_ID = ?",
            arrayOf(words.id.toString()))
        db.close()
    }
    fun fetchWords():List<Words>{

        // array of columns to fetch
        val columns = arrayOf(COLUMN_WORD_ID, COLUMN_WORD_TITLE, COLUMN_WORD_MEANING)

        // sorting order
        val sortOder = "$COLUMN_WORD_TITLE ASC"
        val wordlist = ArrayList<Words>()
        val db= this.writableDatabase

        // query the user table

        val cursor = db.query(TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            sortOder)
        if (cursor.moveToFirst()){
            do {
                val words = Words(id = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_ID)).toInt(),
                    title = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_TITLE)),
                    meaning = cursor.getString(cursor.getColumnIndex(COLUMN_WORD_MEANING)))

                wordlist.add(words)

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return  wordlist
    }
    //Create Query to drop our Table


    private val DROP_WORD_TABLE = " DROP TABLE IF EXISTS $TABLE_NAME"



    companion object {
        private  val DATABASE_VERSION = 1

        private val DATABASE_NAME = "WordsDB.db"

        private val  TABLE_NAME = "words"
        private val COLUMN_WORD_ID = "words_id"
        private val COLUMN_WORD_TITLE = "words_title"
        private val COLUMN_WORD_MEANING = "words_meaning"


    }

}