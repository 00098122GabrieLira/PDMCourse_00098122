package com.gala00098122.peliculas.data.repositories.offlineMovies.popularMoviesRepository

import com.gala00098122.peliculas.data.api.KtorClient
import com.gala00098122.peliculas.data.api.movies.getMovies.GetMoviesResponseDTO
import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDTO
import com.gala00098122.peliculas.data.api.movies.getMovies.toPopularEntity
import com.gala00098122.peliculas.data.database.dao.PopularMovieDAO
import com.gala00098122.peliculas.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.gala00098122.peliculas.data.database.entities.toModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PopularMovieOfflineRepositoryImpl(private val popularMovieDAO: PopularMovieDAO) :
  PopularMovieOfflineRepository {
  
  override fun getPopularMovies(): Flow<List<Movie>> {
    return popularMovieDAO.getPopularMovies().map { list -> list.map { it.toModel() } }
  }
  
  override fun getPopularMovieById(id: Int): Flow<Movie?> {
    return popularMovieDAO.getPopularMovieById(id).map { it?.toModel() }
  }
  
  override suspend fun refreshPopular() {
    val popularMovies = fetchPopular()
    return popularMovieDAO.upsertAll(popularMovies.map { it.toPopularEntity() })
  }
  
  override suspend fun refreshPopularMovieById(id: Int) {
    val movie = fetchById(id)
    return popularMovieDAO.upsert(movie.toPopularEntity())
  }
}

private suspend fun fetchPopular(): List<MovieDTO> =
  KtorClient.client.get("movie/popular") {
    parameter("language", "es-ES")
    parameter("page", 1)
  }.body<GetMoviesResponseDTO>().results

private suspend fun fetchById(id: Int): MovieDTO =
  KtorClient.client.get("movie/$id") {
    parameter("language", "es-ES")
  }.body()