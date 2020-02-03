package com.example.sampleapp.data.network

import com.example.sampleapp.data.models.JsonModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    /**
     * Get the list of the Rows from the API
     */
    @GET("s/2iodh4vg0eortkl/facts.json.")
    fun getPosts(): Observable<JsonModel>
}