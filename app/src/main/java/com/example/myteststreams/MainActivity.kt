package com.example.myteststreams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.myteststreams.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
// TODO
//    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        TODO Coroutines
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch{
                loadData()
            }
        }

//        TODO Использование Thread, потоков
//        binding.buttonLoad.setOnClickListener { loadData() }
    }

    private suspend fun loadData() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        val city = loadCity()
        binding.tvLocation.text = city
        val temp = loadTemperature(city)
        binding.tvTemperature.text = temp.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true

    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            getString(R.string.loading_temperature_toast, city),
            Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 17
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Moscow"
    }


//TODO Использование Thread, потоков
//    private fun loadData() {
//        Log.d("MainActivity", "Load started: $this")
//        binding.progress.isVisible = true
//        binding.buttonLoad.isEnabled = false
//        loadCity {
//            binding.tvLocation.text = it
//            loadTemperature(it) {
//                binding.tvTemperature.text = it.toString()
//                binding.progress.isVisible = false
//                binding.buttonLoad.isEnabled = true
//                Log.d("MainActivity", "Load started: $this")
//            }
//        }
//    }
// TODO
//    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
//        thread {
//            runOnUiThread {
//                Toast.makeText(
//                    this,
//                    getString(R.string.loading_temperature_toast, city),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            Thread.sleep(5000)
//            runOnUiThread {
//                callback.invoke(17)
//            }
//        }
//    }
//    private fun loadCity(callback: (String) -> Unit) {
//        thread {
//            Thread.sleep(5000)
//            runOnUiThread {
//                callback.invoke("Moscow")
//            }
//        }
//    }
}