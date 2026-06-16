package com.gala00098122.tarea_room.data

import android.content.Context
import com.gala00098122.tarea_room.data.database.AppDatabase
import com.gala00098122.tarea_room.data.repository.localRepository.LocalRepository
import com.gala00098122.tarea_room.data.repository.localRepository.LocalRepositoryImpl
import com.gala00098122.tarea_room.data.repository.questionRepository.QuestionRepository
import com.gala00098122.tarea_room.data.repository.questionRepository.QuestionRepositoryImpl

class AppProvider(context: Context) {
  
  private val appDatabase = AppDatabase.getDatabase(context)
  private val localDAO = appDatabase.localDAO()
  private val questionDAO = appDatabase.questionDAO()
  
  private val localRepository: LocalRepository = LocalRepositoryImpl(localDAO)
  
  private val questionRepository: QuestionRepository =
    QuestionRepositoryImpl(questionDAO)
  
  fun provideLocalRepository(): LocalRepository {
    return localRepository
  }
  
  fun provideQuestionRepository(): QuestionRepository {
    return questionRepository
  }
  
}