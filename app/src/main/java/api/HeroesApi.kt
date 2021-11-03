package api
import dto.SuperHero
import retrofit2.Call
import retrofit2.http.GET



interface HeroesApi {
    @GET("marvel")
    suspend fun getSuperHeroes(): List<SuperHero>

    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}