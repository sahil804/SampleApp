package com.example.sampleapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
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


    var liveDataTile: MutableLiveData<String> = MutableLiveData()

    /**
     * Get the list of the Rows from the API
     */
    fun getRows():LiveData<Resource<List<Rows>>> {
        // Rather than using LiveDataInterceptor, creating instance of liveData and updating it
        var liveData: MutableLiveData<Resource<List<Rows>>> = MutableLiveData()
        apiInterface.getPosts().enqueue(object : Callback<JsonModel> {
            override fun onFailure(call: Call<JsonModel>, t: Throwable) {
                val rowsList: List<Rows> = ArrayList()
                // This will give title as offline
                liveData.value = Resource.error(t.toString(), rowsList)
                liveDataTile.value = "Offline"
            }

            override fun onResponse(call: Call<JsonModel>, response: Response<JsonModel>) {
                liveData.value = Resource.success(response.body()!!.rows)
                liveDataTile.value = response.body()?.title ?: "Default"
            }

        });
        return liveData
    }

}