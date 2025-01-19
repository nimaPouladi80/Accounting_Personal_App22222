package com.example.personalcalculatingapplication


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.personalcalculatingapplication.sql.CalculatingDatabaseHelper
import com.example.personalcalculatingapplication.sql.G
import com.example.personalcalculatingapplication.ui.SplashActivity

class ReminderWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {

        val calculatingDatabaseHelper = CalculatingDatabaseHelper(context , G.DBNAME , 1)

        val mList = calculatingDatabaseHelper.getAllCheks()
        for( i in mList){
            val cDate = i.endDate.replace("/", "-")+" 00:00:00"
            if (i.hasNotify == "1" &&  isTodayDate(cDate)== 0L){
                val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("حسابداری شخصی")
                    .setContentText("اعلان ${i.subject} در تاریخ ${i.endDate}")
                    .setAutoCancel(true)

                val notificationIntent = Intent(context, SplashActivity::class.java)

                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                val intent = PendingIntent.getActivity(
                    context, 0,
                    notificationIntent, 0
                )
                mBuilder.setContentIntent(intent)

                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(0, mBuilder.build())

                break
            }

        }

        val entireList = calculatingDatabaseHelper.getAllEntire()
        for( i in entireList){
            val cDate = i.date.replace("/", "-")+" 00:00:00"
            if (i.hasNotify == "1" &&  isTodayDate(cDate)== 0L){
                val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("حسابداری شخصی")
                    .setContentText("اعلان ${i.subject} در تاریخ ${i.date}")
                    .setAutoCancel(true)

                val notificationIntent = Intent(context, SplashActivity::class.java)

                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                val intent = PendingIntent.getActivity(
                    context, 0,
                    notificationIntent, 0
                )
                mBuilder.setContentIntent(intent)

                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(1, mBuilder.build())

                break
            }

        }

        val incomeList = calculatingDatabaseHelper.getAllIncome()
        for( i in incomeList){
            val cDate = i.date.replace("/", "-")+" 00:00:00"
            if (i.hasNotify == "1" &&  isTodayDate(cDate)== 0L){
                val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("حسابداری شخصی")
                    .setContentText("اعلان ${i.subject} در تاریخ ${i.date}")
                    .setAutoCancel(true)

                val notificationIntent = Intent(context, SplashActivity::class.java)

                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                val intent = PendingIntent.getActivity(
                    context, 0,
                    notificationIntent, 0
                )
                mBuilder.setContentIntent(intent)

                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(2, mBuilder.build())

                break
            }

        }

        val loansList = calculatingDatabaseHelper.getAllLoans()
        for( i in loansList){
            val cDate = i.date.replace("/", "-")+" 00:00:00"
            if (i.hasNotify == "1" &&  isTodayDate(cDate)== 0L){
                val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("حسابداری شخصی")
                    .setContentText("اعلان ${i.subject} در تاریخ ${i.date}")
                    .setAutoCancel(true)

                val notificationIntent = Intent(context, SplashActivity::class.java)

                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                val intent = PendingIntent.getActivity(
                    context, 0,
                    notificationIntent, 0
                )
                mBuilder.setContentIntent(intent)

                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(3, mBuilder.build())

                break
            }

        }

        val paysList = calculatingDatabaseHelper.getAllPayment()
        for( i in paysList){
            val cDate = i.date.replace("/", "-")+" 00:00:00"
            if (i.hasNotify == "1" &&  isTodayDate(cDate)== 0L){
                val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("حسابداری شخصی")
                    .setContentText("اعلان ${i.subject} در تاریخ ${i.date}")
                    .setAutoCancel(true)

                val notificationIntent = Intent(context, SplashActivity::class.java)

                notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

                val intent = PendingIntent.getActivity(
                    context, 0,
                    notificationIntent, 0
                )
                mBuilder.setContentIntent(intent)

                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(4, mBuilder.build())

                break
            }

        }

        return Result.success()
    }

}