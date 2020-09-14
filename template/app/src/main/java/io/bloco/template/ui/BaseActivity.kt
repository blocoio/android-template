package io.bloco.template.ui

import androidx.appcompat.app.AppCompatActivity
import io.bloco.template.App

abstract class BaseActivity : AppCompatActivity() {

    private val app get() = applicationContext as App
}