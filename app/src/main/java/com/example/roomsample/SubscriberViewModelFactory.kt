package com.example.roomsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomsample.db.SubscriberRepositry
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val repositry: SubscriberRepositry):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Subcriber_viewmodel::class.java)) {
        return Subcriber_viewmodel(repositry)as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}