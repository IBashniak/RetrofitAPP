package com.amigo.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import dto.SuperHero

import android.widget.ArrayAdapter

import client.RetrofitClient
import com.amigo.retrofitapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        RetrofitClient.heroesApi.getSuperHeroes().enqueue(object : Callback<List<SuperHero>> {
            override fun onResponse(
                call: Call<List<SuperHero>>,
                response: Response<List<SuperHero>>
            ) {
                val heroesList = response.body()?.map { it ->
                    it.superHeroName
                }?: emptyList<SuperHero>()

                binding.superListView.adapter =
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        heroesList
                    )
            }

            override fun onFailure(call: Call<List<SuperHero>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}