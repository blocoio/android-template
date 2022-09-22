package io.bloco.template.theme

import android.util.Log
import android.util.Log.d
import java.util.logging.Logger

class MainViewModel {

    val name: String = "Example"

    init {
        Log.d("MainViewModel", "I was just created")
    }
}