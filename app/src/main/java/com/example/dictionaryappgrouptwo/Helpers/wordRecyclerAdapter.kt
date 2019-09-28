package com.example.dictionaryappgrouptwo.Helpers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryappgrouptwo.Model.Words
import com.example.dictionaryappgrouptwo.R
import com.example.dictionaryappgrouptwo.ViewWords

class wordRecyclerAdapter (private val listWords:List<Words>, internal var context: Context) : RecyclerView.Adapter <wordRecyclerAdapter.WordviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):WordviewHolder{
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_list_card,parent,false)
        return  WordviewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return listWords.size
    }

    override fun onBindViewHolder(holder: WordviewHolder, position: Int) {


        holder.textTitle.text = listWords[position].title


        //set onclick listner on a users data
        holder.itemView.setOnClickListener(View.OnClickListener {

            val  i = Intent(context, ViewWords::class.java)

            // pass the details of the user to the next activity

            i.putExtra("id", listWords[position] .id)
            i.putExtra("title",listWords[position].title)
            i.putExtra("meaning", listWords[position].meaning)

            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        })



    }

    inner class WordviewHolder(view: View):RecyclerView.ViewHolder(view){

        var textTitle : TextView



        init {
            textTitle = view.findViewById(R.id.word_title)as TextView


        }

    }

}