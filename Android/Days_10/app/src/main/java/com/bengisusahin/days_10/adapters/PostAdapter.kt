package com.bengisusahin.days_10.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bengisusahin.days_10.R
import com.bengisusahin.days_10.models.Post

class PostAdapter(private val list: List<Post>) : RecyclerView.Adapter<PostItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemHolder {
        return PostItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // onCreateViewHolder da olusturdugun holder burda tetikleniyor
    override fun onBindViewHolder(holder: PostItemHolder, position: Int) {
        holder.bindItem(list[position])
        /*
        holder.itemView.setOnClickListener {
            Log.d("click", "onBindViewHolder: click")
        }
         */
    }

}