package com.gala00098122.tarea_room.data

import android.content.Context
import com.gala00098122.tarea_room.data.database.AppDatabase
import com.gala00098122.tarea_room.data.remote.options.OptionsOfflineRepository
import com.gala00098122.tarea_room.data.remote.options.OptionsOfflineRepositoryImpl
import com.gala00098122.tarea_room.data.remote.questions.QuestionsOfflineRepository
import com.gala00098122.tarea_room.data.remote.questions.QuestionsOfflineRepositoryImpl

class AppProvider(context: Context) {
  
  private val appDatabase = AppDatabase.getDatabase(context)
  private val optionDAO = appDatabase.optionDAO()
  private val questionDAO = appDatabase.questionDAO()
  
  private val optionRepository: OptionsOfflineRepository = OptionsOfflineRepositoryImpl(optionDAO)
  
  private val questionRepository: QuestionsOfflineRepository =
    QuestionsOfflineRepositoryImpl(questionDAO)
  
  fun provideOptionsOfflineRepository(): OptionsOfflineRepository {
    return optionRepository
  }
  
  fun provideQuestionOfflineRepository(): QuestionsOfflineRepository {
    return questionRepository
  }
  
}