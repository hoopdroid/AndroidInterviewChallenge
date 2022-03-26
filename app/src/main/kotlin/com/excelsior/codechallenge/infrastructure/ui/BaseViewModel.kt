package com.excelsior.codechallenge.infrastructure.ui

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections

interface BaseViewModel {

    val direction: LiveData<NavDirections>

    val showError: LiveData<String>
}