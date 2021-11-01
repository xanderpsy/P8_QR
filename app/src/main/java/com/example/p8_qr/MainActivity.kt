package com.example.p8_qr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.p8_qr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback

import com.journeyapps.barcodescanner.ScanContract

import com.journeyapps.barcodescanner.ScanOptions

import androidx.activity.result.ActivityResultLauncher
import com.journeyapps.barcodescanner.ScanIntentResult


class MainActivity : AppCompatActivity() {
    private lateinit var binding  : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnScanner.setOnClickListener {

            barcodeLauncher.launch(ScanOptions()) }
    }

    private val barcodeLauncher = registerForActivityResult(
        ScanContract()

    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        } else {
            binding.textView.text = "lo que se genero es:"
            binding.tvLink.text = result.contents
            Toast.makeText(
                this,
                "Scanned: " + result.contents,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}