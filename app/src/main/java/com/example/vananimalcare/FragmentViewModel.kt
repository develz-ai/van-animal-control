package com.example.vananimalcare

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    val data = MutableLiveData<String>()

    fun setData(newData : String){
        data.value = newData
    }
}