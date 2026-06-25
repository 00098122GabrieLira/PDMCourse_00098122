package com.gala00098122.peliculas.data.repositories.offlineMovies.upComingMoviesRepository

import com.gala00098122.peliculas.data.api.KtorClient
import com.gala00098122.peliculas.data.api.movies.getMovies.GetMoviesResponseDTO
import com.gala00098122.peliculas.data.api.movies.getMovies.MovieDTO
import com.gala00098122.peliculas.data.api.movies.getMovies.toUpComingEntity
import com.gala00098122.peliculas.data.database.dao.UpComingMovieDAO
import com.gala00098122.peliculas.data.database.entities.toModel
import com.gala00098122.peliculas.data.model.Movie
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UpComingMovieOfflineRepositoryImpl(private val upComingMovieDAO: UpComingMovieDAO) :
  UpComingMovieOfflineRepository {
  
  override fun getUpComingMovies(): Flow<List<Movie>> {
    return upComingMovieDAO.getUpComingMovies().map { list -> list.map { it.toModel() } }
  }
  
  override fun getUpComingMovieById(id: Int): Flow<Movie?> {
    return upComingMovieDAO.getUpComingMovieById(id).map { it?.toModel() }
  }
  
  override suspend fun refreshUpComing() {
    val upComingMovies = fetchUpComing()
    return upComingMovieDAO.upsertAll(upComingMovies.map { it.toUpComingEntity() })
  }
  
  override suspend fun refreshUpComingMovieById(id: Int) {
    val movie = fetchById(id)
    return upComingMovieDAO.upsert(movie.toUpComingEntity())
  }
}

private suspend fun fetchUpComing(): List<MovieDTO> =
  KtorClient.client.get("movie/upcoming") {
    parameter("language", "es-ES")
    parameter("page", 1)
  }.body<GetMoviesResponseDTO>().results

private suspend fun fetchById(id: Int): MovieDTO =
  KtorClient.client.get("movie/$id") {
    parameter("language", "es-ES")
  }.body()