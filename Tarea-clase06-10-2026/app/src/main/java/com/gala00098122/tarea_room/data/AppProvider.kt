package com.gala00098122.tarea_room.data

import android.content.Context
import com.gala00098122.tarea_room.data.database.AppDatabase
import com.gala00098122.tarea_room.data.repository.optionRepository.OptionRepository
import com.gala00098122.tarea_room.data.repository.optionRepository.OptionRepositoryImpl
import com.gala00098122.tarea_room.data.repository.questionRepository.QuestionRepository
import com.gala00098122.tarea_room.data.repository.questionRepository.QuestionRepositoryImpl

class AppProvider(context: Context) {
  
  private val appDatabase = AppDatabase.getDatabase(context)
  private val optionDAO = appDatabase.optionDAO()
  private val questionDAO = appDatabase.questionDAO()
  
  private val optionRepository: OptionRepository = OptionRepositoryImpl(optionDAO)
  
  private val questionRepository: QuestionRepository =
    QuestionRepositoryImpl(questionDAO)
  
  fun provideOptionRepository(): OptionRepository {
    return optionRepository
  }
  
  fun provideQuestionRepository(): QuestionRepository {
    return questionRepository
  }
  
}