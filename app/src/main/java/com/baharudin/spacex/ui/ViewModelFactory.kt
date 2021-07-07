package com.baharudin.spacex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baharudin.spacex.network.SpaceRepository

class ViewModelFactory(
    private val repository: SpaceRepository
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpaceViewModel(repository) as T
    }
}