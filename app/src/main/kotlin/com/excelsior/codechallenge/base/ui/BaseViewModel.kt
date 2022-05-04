package com.excelsior.codechallenge.base.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.excelsior.codechallenge.base.utils.SingleLiveEvent

interface BaseViewModel {

    val direction: MutableLiveData<NavDirections>
    val showError: LiveData<String>
}