package com.excelsior.codechallenge.presentation.eventScreen.ui

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.base.ui.BaseViewModel

interface EventScreenViewModel : BaseViewModel {
    fun getEvent(id: String)
    fun observeEvent(): LiveData<EventScreenState>
}