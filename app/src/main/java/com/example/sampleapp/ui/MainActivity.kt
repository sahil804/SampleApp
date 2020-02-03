package com.example.sampleapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityMainBinding
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
        //setContentView(R.layout.activity_main)
        //component.inject(this)
        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.setLifecycleOwner(this)
        AndroidInjection.inject(this)
        //recyclerView = findViewById(R.id.rv_rows)
        var adapter = RowListAdapter()
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
        mainBinding.rvRows.adapter =adapter
        mainBinding.rvRows.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        //viewModel = factory.create(MainViewModel::class.java)
        mainBinding.searchResult = viewModel.listRow
        viewModel.listRow.observe(this, Observer { listRows ->
            listRows?.data?.let { adapter.updateList(it) }
        })

        viewModel.start("start")

        mainBinding.callback = object : TryAgainCallBack {
            override fun tryAgain() {
                Log.d("Sahil","try again !!")
                viewModel.refresh()
            }
        }

    }
}
