package com.gala00098122.peliculas

import android.app.Application
import com.gala00098122.peliculas.data.AppProvider

class PeliculasApplication: Application() {
val appProvider by lazy { AppProvider(this) }
}