package com.gala00098122.tarea_room.data

import android.content.Context
import com.gala00098122.tarea_room.data.database.AppDatabase
import com.gala00098122.tarea_room.data.repository.localRepository.LocalRepository
import com.gala00098122.tarea_room.data.repository.localRepository.LocalRepositoryImpl

class AppProvider(context: Context) {
  
  private val appDatabase = AppDatabase.getDatabase(context)
  private val localDAO = appDatabase.localDAO()
  
  private val localRepository: LocalRepository = LocalRepositoryImpl(localDAO)
  
  fun provideLocalRepository(): LocalRepository {
    return localRepository
  }
}