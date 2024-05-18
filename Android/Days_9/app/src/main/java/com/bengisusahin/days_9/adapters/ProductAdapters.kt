package com.bengisusahin.days_9.adapters

import Product
import Products
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bengisusahin.days_9.R
import com.bumptech.glide.Glide

class ProductAdapters(private val context:Activity, private var arr: List<Product>) :
    ArrayAdapter<Product>(context, R.layout.product_row, arr)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.product_row, null, true)
        val dt = arr.get(position)


        val r_title= rootView.findViewById<TextView>(R.id.r_title)
        val r_image= rootView.findViewById<ImageView>(R.id.r_image)
        val r_price= rootView.findViewById<TextView>(R.id.r_price)


        r_title.setText(dt.title)
        r_price.setText("${dt.price}₺")

        val url = dt.thumbnail
        Glide.with(rootView).load(url). into(r_image)

        Log.d("this", "row call")
        rootView.setOnClickListener {
            it.setBackgroundColor(Color.RED)
        }
        return rootView
    }
}

/*
class ProductAdapters(context:Context, arr: List<Product>) :
    ArrayAdapter<Product>(context, R.layout.product_row, arr!!)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rootView = convertView

        val dt = getItem(position)

        if (rootView == null){
            rootView = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)
        }
        val r_title= rootView!!.findViewById<TextView>(R.id.r_title)
        val r_image= rootView.findViewById<ImageView>(R.id.r_image)
        val r_price= rootView.findViewById<TextView>(R.id.r_price)

        if (dt != null) {
            r_title.setText(dt.title)
        }
        if (dt != null) {
            r_price.setText("${dt.price}")
        }

        return rootView
    }
}
 */