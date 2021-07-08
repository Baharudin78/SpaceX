package com.baharudin.spacex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.spacex.data.CapsuleResponse
import com.baharudin.spacex.data.DragonResponse
import com.baharudin.spacex.network.SpaceRepository
import com.baharudin.spacex.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SpaceViewModel(
    val repository: SpaceRepository
) : ViewModel() {
    val getAllCapsule : MutableLiveData<Resource<CapsuleResponse>> = MutableLiveData()
    val getAllDragon : MutableLiveData<Resource<DragonResponse>> = MutableLiveData()

    init {
        getAllCapsule()
        getAllDragon()
    }

    fun getAllCapsule() = viewModelScope.launch {
        getAllCapsule.postValue(Resource.Loading())
        val response = repository.getAllCapsule()
        getAllCapsule.postValue(handleGetAllCapsule(response))
    }
    fun getAllDragon() = viewModelScope.launch {
        getAllDragon.postValue(Resource.Loading())
        val response = repository.getAllDragon()
        getAllDragon.postValue(handleGetAllDragon(response))
    }
    private fun handleGetAllCapsule(response : Response<CapsuleResponse>) : Resource<CapsuleResponse> {
        if (response.isSuccessful) {
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleGetAllDragon(response: Response<DragonResponse>) : Resource<DragonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}