package ru.mirea.populationcensus.Domain.DataBase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel() {
    val messageForCreateFragment: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}