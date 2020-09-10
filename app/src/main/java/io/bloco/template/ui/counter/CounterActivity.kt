package io.bloco.template.ui.counter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.bloco.template.R
import io.bloco.template.common.di.ViewModelFactory
import io.bloco.template.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_counter.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CounterActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CounterViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CounterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_counter)
        initView()

        viewModel.getValue().onEach {
            value.text = it.toString()
        }.launchIn(lifecycleScope)

        viewModel.getErrors().onEach {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    private fun initView() {
        fab_increment.setOnClickListener { this.incrementCounter() }
        fab_decrement.setOnClickListener { this.decrementCounter() }
    }

    private fun incrementCounter() {
        viewModel.incrementCounter()
    }

    private fun decrementCounter() {
        viewModel.decrementCounter()
    }

}