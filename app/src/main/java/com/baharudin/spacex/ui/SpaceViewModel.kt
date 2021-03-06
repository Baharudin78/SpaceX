package com.baharudin.spacex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharudin.spacex.data.company.CompanyResponse
import com.baharudin.spacex.data.crew.CrewResponse
import com.baharudin.spacex.data.dragon.DragonResponse
import com.baharudin.spacex.data.rocket.RocketResponse
import com.baharudin.spacex.data.ship.ShipResponse
import com.baharudin.spacex.network.SpaceRepository
import com.baharudin.spacex.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SpaceViewModel(
    val repository: SpaceRepository
) : ViewModel() {
    val getAllDragon : MutableLiveData<Resource<DragonResponse>> = MutableLiveData()
    val getAllShip : MutableLiveData<Resource<ShipResponse>> = MutableLiveData()
    val getAllRocket : MutableLiveData<Resource<RocketResponse>> = MutableLiveData()
    val getAllCrew : MutableLiveData<Resource<CrewResponse>> = MutableLiveData()
    val getCompanyInfo : MutableLiveData<Resource<CompanyResponse>> = MutableLiveData()

    init {
        getAllDragon()
        getAllShip()
        getAllRocket()
        getAllCrew()
        getCompanyInfo()
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
    fun getAllRocket() = viewModelScope.launch {
        getAllRocket.postValue(Resource.Loading())
        val responseRocket = repository.getAllRocket()
        getAllRocket.postValue(handleGetAllRocket(responseRocket))
    }
    fun getAllCrew() = viewModelScope.launch {
        getAllCrew.postValue(Resource.Loading())
        val responseCrew = repository.getAllCrew()
        getAllCrew.postValue(handleGetAllCrew(responseCrew))
    }
    fun getCompanyInfo() = viewModelScope.launch {
        getCompanyInfo.postValue(Resource.Loading())
        val responseCompany = repository.getCompanyInfo()
        getCompanyInfo.postValue(handleCompanyInfo(responseCompany))
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

    private fun handleGetAllRocket(response : Response<RocketResponse>) : Resource<RocketResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultRocket ->
                return Resource.Success(resultRocket)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGetAllCrew(response : Response<CrewResponse>) : Resource<CrewResponse> {
        if(response.isSuccessful) {
            response.body()?.let {  resultCrew ->
                return Resource.Success(resultCrew)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleCompanyInfo(response : Response<CompanyResponse>) : Resource<CompanyResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultCompany ->
                return Resource.Success(resultCompany)
            }
        }
        return Resource.Error(response.message())
    }


}