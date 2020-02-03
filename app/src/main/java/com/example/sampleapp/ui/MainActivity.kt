package com.example.sampleapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.lifecycleOwner = this
        // Dagger injection
        AndroidInjection.inject(this)

        //placing toolbar in place of actionbar
        setSupportActionBar(mainBinding.toolBar);
        val adapter = RowListAdapter()
        mainBinding.rvRows.adapter =adapter
        mainBinding.rvRows.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        //binding result for recycler view
        mainBinding.searchResult = viewModel.listRow

        //binding title based on n/w response
        mainBinding.title = viewModel.title
        viewModel.listRow.observe(this, Observer { listRows ->
            listRows?.data?.let { adapter.updateList(it) }
        })

        //binding for reload button and swipeRefresh
        mainBinding.callback = object : TryAgainCallBack {
            override fun tryAgain() {
                viewModel.reloadRows()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.menu_clear -> {
                viewModel.clearRows()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}