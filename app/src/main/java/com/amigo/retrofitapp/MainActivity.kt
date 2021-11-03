package com.amigo.retrofitapp


import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import dto.SuperHero
import client.RetrofitClient
import com.amigo.retrofitapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var coroutineScope = createCoroutineScope()
    private fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(
            "MainActivity",
            "Coroutine exception",
            throwable
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel("It's time")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getSuperHeroes()
    }

    private suspend fun onResponse(heroes: List<SuperHero>) = withContext(Dispatchers.Main) {
        val heroesNameList = heroes.map { it ->
            it.superHeroName
        }

        binding.superListView.adapter =
            ArrayAdapter(
                applicationContext,
                R.layout.simple_list_item_1,
                heroesNameList
            )
    }

    private fun getSuperHeroes() {
        coroutineScope.launch(exceptionHandler) {
            val heroes = RetrofitClient.heroesApi.getSuperHeroes()
            onResponse(heroes)
        }
    }
}