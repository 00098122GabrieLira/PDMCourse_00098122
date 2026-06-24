package com.gala00098122.peliculas.data.repositories.movieRepository

import com.gala00098122.peliculas.data.api.KtorClient
import com.gala00098122.peliculas.data.api.movies.getMovies.GetMoviesResponseDto
import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDto
import com.gala00098122.peliculas.data.api.movies.getMovies.toModel
import com.gala00098122.peliculas.data.api.movies.upComing.GetUpcomingMoviesResponseDto
import com.gala00098122.peliculas.data.model.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiRepository : MovieRepository {
  override suspend fun getMovies(): Result<List<Movie>> {
    try {
      
      val response: GetMoviesResponseDto = KtorClient.client.get("movie/popular") {
        parameter("language", "es-ES")
        parameter("page", 1)
      }.body()
      
      return Result.success(response.results.map { movieDto -> movieDto.toModel() })
      
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
  
  override suspend fun getMovieById(id: Int): Result<Movie> {
    
    try {
      
      val response: MovieDto = KtorClient.client.get("movie/$id") {
        parameter("language", "es-ES")
      }.body()
      
      return Result.success(response.toModel())
      
    } catch (e: Exception) {
      return Result.failure(e)
    }
    
  }
  
  override suspend fun getUpcomingMovies(): Result<List<Movie>> {
    try {
      
      val response: GetUpcomingMoviesResponseDto = KtorClient.client.get("movie/upcoming") {
        parameter("language", "es-ES")
        parameter("page", 1)
      }.body()
      
      return Result.success(response.results.map { movieDTO -> movieDTO.toModel() })
      
    } catch (e: Exception) {
      return Result.failure(e)
    }
  }
}
