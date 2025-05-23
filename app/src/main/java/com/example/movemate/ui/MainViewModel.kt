package com.example.movemate.ui

import androidx.lifecycle.ViewModel
import com.example.movemate.ui.home.BottomNavigationDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MainUiState(
    val searchValue: String = "",
    val searchIsFocused: Boolean = false,
    val showBottomBar: Boolean = true,
    val bottomBarIndex: Int = BottomNavigationDirections.Home.index
)

class MainViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()


    fun updateSearchValue(value: String) {
        _uiState.update { it.copy(searchValue = value) }
    }

    fun updateSearchFocus(state: Boolean) {
        _uiState.update { it.copy(searchIsFocused = state, showBottomBar = !state) }
    }

    fun updateBottomBarVisibility(state: Boolean) {
        _uiState.update { it.copy(showBottomBar = state, searchIsFocused = false) }
    }

    fun updateBottomBarIndex(index: Int) {
        _uiState.update { it.copy(bottomBarIndex = index) }
    }
}