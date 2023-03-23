package com.example.homework2

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat


class MainActivity : AppCompatActivity() {
    lateinit var webSiteEditText: EditText
    lateinit var locationEditText: EditText
    lateinit var shareEditText: EditText
    lateinit var siteButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webSiteEditText = findViewById(R.id.website_edittext)
    }

    fun openWebsite(view: View) {
        val url = webSiteEditText.text.toString()
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        try {
            startActivity(intent)
        }catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "Cannot handle this",
                Toast.LENGTH_LONG).show()
        }
    }


    fun openLocation(view: View) {
        locationEditText = findViewById(R.id.location_edittext)
        val location = locationEditText.text.toString()
        val locationUri = Uri.parse("geo:0,0?q=$location")
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = locationUri
        }
        try {
            startActivity(intent)
        }catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "Cannot handle this",
                Toast.LENGTH_LONG).show()
        }
    }

    fun shareText(view: View) {
        shareEditText = findViewById(R.id.share_edittext)
        val text = shareEditText.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with:")
            .setText(text)
            .startChooser()
    }


}
