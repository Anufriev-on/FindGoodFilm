package com.application.anufriev.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.application.anufriev.data.Entity.Film
import com.application.anufriev.view.notifications.NotificationConstants
import com.application.anufriev.view.notifications.NotificationHelper

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.getBundleExtra(NotificationConstants.FILM_BUNDLE_KEY)
        val film: Film = bundle?.get(NotificationConstants.FILM_KEY) as Film

        NotificationHelper.createNotification(context!!, film)
    }
}