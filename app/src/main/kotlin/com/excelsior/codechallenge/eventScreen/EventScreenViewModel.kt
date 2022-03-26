package com.excelsior.codechallenge.eventScreen

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.infrastructure.ui.BaseViewModel

interface EventsScreenViewModel : BaseViewModel {
    fun getEvent(id: String)
    fun observeEvent(): LiveData<EventItemVO>
}