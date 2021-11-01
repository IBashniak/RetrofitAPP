package client

import api.HeroesApi
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitClient {
    val heroesApi: HeroesApi = Retrofit.Builder().baseUrl(HeroesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(HeroesApi::class.java)
}