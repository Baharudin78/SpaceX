package com.baharudin.spacex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baharudin.spacex.data.Capsule
import com.baharudin.spacex.data.CapsuleResponse
import com.baharudin.spacex.network.SpaceRepository
import com.baharudin.spacex.util.Resource

class SpaceViewModel(
    val repository: SpaceRepository
) : ViewModel() {
    val getAllCapsule : MutableLiveData<Resource<CapsuleResponse>> = MutableLiveData()


}