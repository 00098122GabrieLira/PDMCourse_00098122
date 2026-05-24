package com.gala00098122.peliculas.data.repositories.movieRepository

import com.gala00098122.peliculas.data.api.KtorClient
import com.gala00098122.peliculas.data.api.movies.getMovies.GetMoviesResponseDto
import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDto
import com.gala00098122.peliculas.data.api.movies.getMovies.toModel
import com.gala00098122.peliculas.data.api.movies.upComing.GetUpcomingMoviesResponseDto
import com.gala00098122.peliculas.model.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiRepository : MovieRepository {
  override suspend fun getMovies(): List<Movie> {
    val response: GetMoviesResponseDto = KtorClient.client.get("movie/popular") {
      parameter("language", "es-ES")
      parameter("page", 1)
    }.body()
    
    return response.results.map { movieDto -> movieDto.toModel() }
  }
  
  override suspend fun getMovieById(id: Int): Movie {
    val response: MovieDto = KtorClient.client.get("movie/$id") {
      parameter("language", "es-ES")
    }.body()
    
    return response.toModel()
  }
  
  override suspend fun getUpcomingMovies(): List<Movie> {
    val response: GetUpcomingMoviesResponseDto = KtorClient.client.get("movie/upcoming") {
      parameter("language", "es-ES")
      parameter("page", 1)
    }.body()
    
    return response.results.map { movieDTO -> movieDTO.toModel() }
  }
}
