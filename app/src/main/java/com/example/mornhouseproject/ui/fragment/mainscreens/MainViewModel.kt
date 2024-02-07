package com.example.mornhouseproject.ui.fragment.mainscreens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mornhouseproject.model.NumberFactModel
import com.example.mornhouseproject.network.MaineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MaineRepository,
) : ViewModel() {
    private val _factList = MutableLiveData<List<NumberFactModel>>()
    val fact: LiveData<List<NumberFactModel>> get() = _factList

    init {
        getSavedList()
    }

    private fun getSavedList() {
        viewModelScope.launch {
            repository.getFactList().collect {
                _factList.postValue(it)
            }
        }
    }

    fun getNumberFact(number: Int) {
        viewModelScope.launch {
            try {
                repository.getNumberFact(number)
            } catch (_: Exception) {

            }
        }
    }

    fun getRandomFact() {
        viewModelScope.launch {
            try {
                repository.getRandomFact()
            } catch (_: Exception) {

            }
        }
    }
}




