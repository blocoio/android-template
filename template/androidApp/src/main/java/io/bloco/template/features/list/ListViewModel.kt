package io.bloco.template.features.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bloco.core.domain.GetBooks
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getBooks: GetBooks
): ViewModel() {
}