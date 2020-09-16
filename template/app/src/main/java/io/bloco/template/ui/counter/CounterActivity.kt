package io.bloco.template.ui.counter

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.bloco.template.R
import io.bloco.template.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_counter.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CounterActivity : BaseActivity() {

    private lateinit var viewModel : CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        setContentView(R.layout.activity_counter)

        fabIncrement.setOnClickListener { viewModel.incrementClick() }
        fabDecrement.setOnClickListener { viewModel.decrementClick() }

        viewModel.value()
            .onEach { value.text = it.toString() }
            .launchIn(lifecycleScope)

        viewModel.errors()
            .onEach {
                showErrorSnackBar()
            }
            .launchIn(lifecycleScope)
    }


    private fun showErrorSnackBar() {
        Snackbar.make(coordinatorLayout, R.string.error_message, Snackbar.LENGTH_SHORT).show()
    }

}