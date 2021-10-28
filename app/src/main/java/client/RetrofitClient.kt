package client

import api.Api
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitClient private constructor() {
    val myApi: Api
    init {
        val retrofit = Retrofit.Builder().baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(Api::class.java)
    }

    companion object {
        @get:Synchronized
        var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
            private set
    }
}