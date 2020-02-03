package com.example.sampleapp.data.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.data.models.JsonModel
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.repository.RowsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor (val apiInterface: ApiInterface) {
    private val TAG = RowsRepository::class.qualifiedName

    var liveData: MutableLiveData<Resource<List<Rows>>> = MutableLiveData();
    fun getRows():LiveData<Resource<List<Rows>>> {
        apiInterface.getPosts().enqueue(object : Callback<JsonModel> {
            override fun onFailure(call: Call<JsonModel>, t: Throwable) {
                val rowsList: List<Rows> = ArrayList()
                liveData.value = Resource.error(t.toString(), rowsList)
            }

            override fun onResponse(call: Call<JsonModel>, response: Response<JsonModel>) {
                Log.d(TAG,"onResponse")
                liveData.value = Resource.success(response.body()!!.rows)
            }

        });
        return liveData
    }

}