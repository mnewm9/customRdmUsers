package com.example.genesysanchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.genesysanchallenge.model.results
import com.example.genesysanchallenge.network.RetroServiceInterface
import com.example.genesysanchallenge.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class UsersViewModel: ViewModel() {
    private val mCurrentSelection = MutableStateFlow<results>(results())
    private var  liveDataList: MutableLiveData<List<results>> = MutableLiveData()

    val usersCurrentSelection : StateFlow<results>
        get() = mCurrentSelection

    fun getLiveDatatObserver(): MutableLiveData<List<results>>{
        return liveDataList
    }

  suspend fun makeApiCall(){

        val retroInstance =RetrofitInstance.getRetroInstance()
        val retroService =retroInstance.create(RetroServiceInterface::class.java)
        val response= retroService.getUsers(100)


      liveDataList.postValue(response.resultsList!! )

    }
    fun select(usersModel: results) {
        mCurrentSelection.value = usersModel
    }
}

