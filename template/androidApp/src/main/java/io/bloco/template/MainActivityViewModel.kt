package io.bloco.template

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.commons.logd
import io.bloco.core.domain.AppSettings
import io.bloco.template.MainActivityViewModel.MainActivityUiState.Loading
import io.bloco.template.MainActivityViewModel.MainActivityUiState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    appSettings: AppSettings
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = appSettings.isFirstOpening().map {
        if (it) {
            // Here you can add new logic
            // for first time opening the app it can be useful to check if theres is existing local
            // data from previous installations, or check and download heavier resources
            appSettings.appOpened()
            logd("Opening the app for the first time")
        }
        Success
    }.stateIn(
        scope = viewModelScope,
        initialValue = Loading,
        started = SharingStarted.WhileSubscribed(1_000)
    )

    sealed interface MainActivityUiState {
        object Loading : MainActivityUiState
        object Success : MainActivityUiState
    }
}
