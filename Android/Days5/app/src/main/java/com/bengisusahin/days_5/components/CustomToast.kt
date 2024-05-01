package com.bengisusahin.days_5.components

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.bengisusahin.days_5.R

class CustomToast(val activity: Activity, val title: String, val detail: String, val duration: Int) {

    val toast = Toast(activity)
    val custom_alert_view = activity.layoutInflater.inflate(R.layout.custom_alert, null)
    lateinit var alertTitle: TextView
    lateinit var alertDetail: TextView

    fun show(){
        alertTitle = custom_alert_view.findViewById(R.id.alert_title)
        alertDetail = custom_alert_view.findViewById(R.id.alert_detail)
        alertTitle.setText(title)
        alertDetail.setText(detail)
        toast.duration = duration
        toast.setGravity(Gravity.CENTER_VERTICAL, 0,0)
        toast.view = custom_alert_view
        toast.show()
    }

}