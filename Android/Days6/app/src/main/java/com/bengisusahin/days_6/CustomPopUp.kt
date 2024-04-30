package com.bengisusahin.days_6

import android.app.Activity
import android.view.View
import android.widget.PopupMenu

class CustomPopUp(activity: Activity, view: View,) {

    val popUp = PopupMenu(activity, view)

    fun makePopUp(menuResId: Int) {
        popUp.menuInflater.inflate(menuResId, popUp.menu)

        val size = popUp.menu.size()
        val list = mutableListOf<Int>()

        for (i in 0..size) {

        }

        popUp.setOnMenuItemClickListener {
            when(it.itemId) {

            }
            true
        }
    }
}