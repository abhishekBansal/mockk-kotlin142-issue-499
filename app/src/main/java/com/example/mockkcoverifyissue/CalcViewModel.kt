package com.example.mockkcoverifyissue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CalcViewModel: ViewModel() {
    fun sum(a: Int, b: Int) {
        viewModelScope.launch {
            performActualSum(a, b)
        }
    }

    private fun performActualSum(a: Int, b: Int): Int {
        return a + b
    }
}