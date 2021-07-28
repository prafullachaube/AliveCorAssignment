package com.example.alivecorassignment.userprofilemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


private val mData = MutableLiveData<HashMap<String,String>>()

class UserProfileViewModel : ViewModel() {

    fun setData(data: HashMap<String,String>?) {
        mData.value = data
    }

    fun getData(): LiveData<HashMap<String,String>?>? {
        return mData
    }

}