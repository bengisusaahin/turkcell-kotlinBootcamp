package com.bengisusahin.days_10.adapters

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bengisusahin.days_10.MainActivity
import com.bengisusahin.days_10.R
import com.bengisusahin.days_10.models.Post

class PostItemHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(itemModel : Post){
        val rTitle = itemView.findViewById<TextView>(R.id.r_title)
        val rUserId = itemView.findViewById<TextView>(R.id.r_userId)

        rTitle.text = itemModel.title
        rUserId.text = itemModel.userId.toString()

        // id ye gerek de yok zaten o verinin içerisindesin
        itemView.setOnClickListener {
            Log.d("bindItem", "onBindViewHolder: click" + itemModel)
            val i = Intent(itemView.context, MainActivity::class.java)
            itemView.context.startActivity(i)
        }
    }

}