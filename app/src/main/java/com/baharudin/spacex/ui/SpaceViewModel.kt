package com.baharudin.spacex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.spacex.data.DragonResponse
import com.baharudin.spacex.data.ShipResponse
import com.baharudin.spacex.network.SpaceRepository
import com.baharudin.spacex.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SpaceViewModel(
    val repository: SpaceRepository
) : ViewModel() {
    val getAllDragon : MutableLiveData<Resource<DragonResponse>> = MutableLiveData()
    val getAllShip : MutableLiveData<Resource<ShipResponse>> = MutableLiveData()


    init {
        getAllDragon()
        getAllShip()
    }


    fun getAllDragon() = viewModelScope.launch {
        getAllDragon.postValue(Resource.Loading())
        val response = repository.getAllDragon()
        getAllDragon.postValue(handleGetAllDragon(response))
    }

    fun getAllShip() = viewModelScope.launch {
        getAllShip.postValue(Resource.Loading())
        val response = repository.getAllShip()
        getAllShip.postValue(handleGetAllShip(response))
    }
    private fun handleGetAllDragon(response: Response<DragonResponse>) : Resource<DragonResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGetAllShip(response : Response<ShipResponse>) : Resource<ShipResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultShip ->
                return Resource.Success(resultShip)
            }
        }
        return Resource.Error(response.message())
    }


}