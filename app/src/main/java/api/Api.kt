package api
import dto.Results
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("marvel")
    fun getSuperHeroes(): Call<List<Results>>

    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}