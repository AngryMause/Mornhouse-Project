package com.example.mornhouseproject.ui.fragment.secondscreens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mornhouseproject.model.NumberFactModel
import com.example.mornhouseproject.room.DBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentViewModel @Inject constructor(private val dbRepository: DBRepository) :
    ViewModel() {

    private val _factModel = MutableLiveData<NumberFactModel>()
    val fact: LiveData<NumberFactModel> get() = _factModel

    fun getDescriptionById(id: Long) {
        viewModelScope.launch {
            _factModel.value = dbRepository.getFactByID(id)
        }
    }
}