package com.baharudin.spacex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.spacex.data.CapsuleResponse
import com.baharudin.spacex.network.SpaceRepository
import com.baharudin.spacex.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SpaceViewModel(
    val repository: SpaceRepository
) : ViewModel() {
    val getAllCapsule : MutableLiveData<Resource<CapsuleResponse>> = MutableLiveData()

    init {
        getAllCapsule()
    }

    fun getAllCapsule() = viewModelScope.launch {
        getAllCapsule.postValue(Resource.Loading())
        val response = repository.getAllCapsule()
        getAllCapsule.postValue(handleGetAllCapsule(response))
    }
    private fun handleGetAllCapsule(response : Response<CapsuleResponse>) : Resource<CapsuleResponse> {
        if (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}