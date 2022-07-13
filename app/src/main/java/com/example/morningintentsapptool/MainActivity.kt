package com.example.morningintentsapptool

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    var buttonSms:Button ?= null
    var buttonEmail:Button ?= null
    var buttonCamera:Button ?= null
    var buttonShare:Button ?= null
    var buttonMpesa:Button ?= null
    var buttonCall:Button ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSms = findViewById(R.id.mBtnSms)
        buttonEmail = findViewById(R.id.mBtnEmail)
        buttonCamera = findViewById(R.id.mBtnCamera)
        buttonShare = findViewById(R.id.mBtnShare)
        buttonMpesa = findViewById(R.id.mBtnMpesa)
        buttonCall = findViewById(R.id.mBtnCall)
        //On click listener
        buttonSms!!.setOnClickListener {
            val uri = Uri.parse("smsto:YOUR_SMS_NUMBER")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "The SMS text")
            startActivity(intent)
        }
         buttonEmail!!.setOnClickListener {
             val emailIntent =
                 Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "dimdimlight000@gmail.com", null))
             emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
             emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
             startActivity(Intent.createChooser(emailIntent, "Send email of your liking to..."))
        }
         buttonCamera!!.setOnClickListener {
             val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             startActivityForResult(takePictureIntent, 1)
        }
         buttonShare!!.setOnClickListener {
             val shareIntent = Intent(Intent.ACTION_SEND)
             shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
             shareIntent.type = "text/plain"
             shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
             startActivity(shareIntent)

         }
         buttonMpesa!!.setOnClickListener {
             val simToolKitLaunchIntent =
                 applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
             simToolKitLaunchIntent?.let { startActivity(it) }

         }
         buttonCall!!.setOnClickListener {
             val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254766598239"))
             if (ContextCompat.checkSelfPermission(
                     this@MainActivity,
                     Manifest.permission.CALL_PHONE
                 ) != PackageManager.PERMISSION_GRANTED
             ) {
                 ActivityCompat.requestPermissions(
                     this@MainActivity,
                     arrayOf(Manifest.permission.CALL_PHONE),
                     1
                 )
             } else {
                 startActivity(intent)
             }
        }
    }
}

//Create an application with 4 pages
//On the 1st page place 3 respective buttons linking you on three different pages
//On the 2nd page, implement a simple calculator
//On the 3rd page, display a website of your choice
//On the last page, implement the six intents covered in today's class