package com.excelsior.codechallenge.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.excelsior.codechallenge.base.utils.SingleLiveEvent
import org.koin.core.component.KoinComponent

open class BaseAndroidViewModel : BaseViewModel, KoinComponent, ViewModel() {

    override val direction = SingleLiveEvent<NavDirections>()
    override val showError = MutableLiveData<String>()
}