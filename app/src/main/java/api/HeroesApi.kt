package api
import dto.SuperHero
import retrofit2.Call
import retrofit2.http.GET



interface HeroesApi {
    @GET("marvel")
    fun getSuperHeroes(): Call<List<SuperHero>>

    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}