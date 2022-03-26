package com.excelsior.codechallenge.infrastructure.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import org.koin.core.component.KoinComponent

open class BaseAndroidViewModel : BaseViewModel, KoinComponent, ViewModel() {

    override val direction = MutableLiveData<NavDirections>()

    override val showError = MutableLiveData<String>()
}