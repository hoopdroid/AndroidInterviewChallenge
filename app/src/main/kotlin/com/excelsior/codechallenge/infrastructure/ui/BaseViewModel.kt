package com.excelsior.codechallenge.infrastructure.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.excelsior.codechallenge.infrastructure.utils.SingleLiveEvent

interface BaseViewModel {

    val direction: MutableLiveData<NavDirections>

    val showError: LiveData<String>
}