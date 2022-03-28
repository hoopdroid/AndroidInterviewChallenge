package com.excelsior.codechallenge.eventScreen.ui

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.infrastructure.ui.BaseViewModel

interface EventScreenViewModel : BaseViewModel {
    fun getEvent(id: String)
    fun observeEvent(): LiveData<EventScreenState>
}