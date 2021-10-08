package io.bloco.template.ui.counter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.bloco.template.R
import io.bloco.template.databinding.ActivityCounterBinding
import io.bloco.template.ui.BaseActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CounterActivity : BaseActivity() {

    private val viewModel : CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Theres a problem with this being generic? think so
        binding = ActivityCounterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.fabIncrement.setOnClickListener { viewModel.incrementClick() }
        binding.fabDecrement.setOnClickListener { viewModel.decrementClick() }

        viewModel.value()
            .onEach { binding.value.text = it.toString() }
            .launchIn(lifecycleScope)

        viewModel.errors()
            .onEach {
                showErrorSnackBar()
            }
            .launchIn(lifecycleScope)
    }


    private fun showErrorSnackBar() {
        Snackbar.make(binding.coordinatorLayout, R.string.error_message, Snackbar.LENGTH_SHORT).show()
    }

}