package com.example.myteststreams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.myteststreams.databinding.ActivityMainBinding
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            binding.progress.isVisible = true
            binding.buttonLoad.isEnabled = false
            val deferredCity: Deferred<String> = lifecycleScope.async {
                val city = loadCity()
                binding.tvLocation.text = city
                city
            }
            val deferredTemp: Deferred<Int> = lifecycleScope.async {
                val temp = loadTemperature()
                binding.tvTemperature.text = temp.toString()
                temp
            }
            lifecycleScope.launch {

                val city = deferredCity.await()
                val temp = deferredTemp.await()
                Toast.makeText(this@MainActivity, "City: $city Temp: $temp", Toast.LENGTH_SHORT)
                    .show()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true

            }
        }

    }

    private suspend fun loadData() {


    }

    private suspend fun loadTemperature(): Int {
        delay(5000)
        return 17
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Moscow"
    }
}