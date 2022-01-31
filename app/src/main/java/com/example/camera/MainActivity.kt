package com.example.camera

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.camera.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

  lateinit var imageView: ImageView
  lateinit var imageButton: Button
  lateinit var deleteButton: Button
  val REQUEST_IMAGE_CAPTURE = 100

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    imageView = findViewById(R.id.imageIV)
    imageButton = findViewById(R.id.imageBtn)
    deleteButton = findViewById(R.id.deleteBtn)

    imageButton.setOnClickListener {
      val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

      try {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
      } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "Error" + e.localizedMessage, Toast.LENGTH_SHORT).show()
      }
    }

    deleteButton.setOnClickListener {
      imageView.setImageDrawable(null)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      val imageBitmap = data?.extras?.get("data") as Bitmap
      imageView.setImageBitmap(imageBitmap)
    } else {
      super.onActivityResult(requestCode, resultCode, data)
    }
  }
}
