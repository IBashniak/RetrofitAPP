package com.amigo.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast

import dto.Results

import android.widget.ArrayAdapter

import client.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var superListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        superListView = findViewById<ListView>(R.id.superListView)

        getSuperHeroes()
    }

    private fun getSuperHeroes() {
        RetrofitClient.instance?.myApi?.getSuperHeroes()?.enqueue(object : Callback<List<Results>> {
            override fun onResponse(
                call: Call<List<Results>>,
                response: Response<List<Results>>
            ) {
                val myHeroList = response.body()
                val oneHeroes = mutableListOf<String>()

                myHeroList?.map { it ->
                    oneHeroes.add(it.superHeroName)
                }

                superListView.adapter =
                    ArrayAdapter(
                        applicationContext,
                        android.R.layout.simple_list_item_1,
                        oneHeroes
                    )
            }

            override fun onFailure(call: Call<List<Results>>, t: Throwable) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}