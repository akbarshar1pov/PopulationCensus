package ru.mirea.populationcensus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {
    val messageForCreateFragment: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}