package com.walmart.countrylistapp
import android.util.Log
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.walmart.countrylistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.countries.observe(this, Observer { countryList ->
            // ✅ Show Toast and Log
            Log.d("MainActivity", "Countries loaded: ${countryList.size}")
            Toast.makeText(this, "Loaded ${countryList.size} countries", Toast.LENGTH_SHORT).show()

            binding.recyclerView.adapter = CountryAdapter(countryList)
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                Log.e("MainActivity", "Error: $it")
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loadCountries()
    }
}
