package com.example.sampleapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.di.MainViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //component.inject(this)
        AndroidInjection.inject(this)
        recyclerView = findViewById(R.id.rv_rows)
        var adapter = MainActivityAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        //viewModel = factory.create(MainViewModel::class.java)
        viewModel.listRow.observe(this, Observer { listRows ->
            adapter.updateList(listRows) })

    }
}
