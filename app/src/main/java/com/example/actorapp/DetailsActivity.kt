package com.example.actorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actorapp.databinding.DetailsActivityBinding

class DetailsActivity: AppCompatActivity() {
   private lateinit var binding: DetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val name=intent.getStringExtra("Name")
        val title=intent.getStringExtra("Title")
        val fees=intent.getStringExtra("Fees")
        val img=intent.getIntExtra("Image",0)

        binding.apply {
            nameid.text = name
            titleid.text = title
            feesid.text=fees
            image.setImageResource(img)
        }
    }
}