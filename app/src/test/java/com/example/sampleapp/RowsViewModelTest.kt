@file:Suppress("IncorrectScope")

package com.example.sampleapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sampleapp.data.models.Resource
import com.example.sampleapp.data.models.Rows
import com.example.sampleapp.data.repository.RowsRepository
import com.example.sampleapp.ui.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito


class RowsViewModelTest {
    //@get:Rule
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val rowsRepository = Mockito.mock(RowsRepository::class.java)

    @Test
    fun testTitle() {
        val titlLiveData = MutableLiveData<String>()
        titlLiveData.value = "testValue"

        whenever(rowsRepository.getTitle())
            .thenReturn(titlLiveData)

        val model = MainViewModel(rowsRepository)
        System.out.print(model.title)
        assert(model.title.value.equals("testValue"))
    }

    @Test
    fun testRowLiveTest() {
        val rowListLive = MutableLiveData<Resource<List<Rows>>>()
        val repos = arrayListOf<Rows>(
            Rows(1, "Beavers", "desc","http://upload.wikimedia.org/wikipedia/commons"),
            Rows(2, "Transportation", "desc","href")
        )
        rowListLive.value = Resource.success(repos)

        whenever(rowsRepository.loadRows())
            .thenReturn(rowListLive)

        whenever(rowsRepository.clearRows())
            .thenReturn(MutableLiveData(Resource.success(repos)))

        val model = MainViewModel(rowsRepository)
        model.reloadRows()
        System.out.println(rowsRepository.loadRows())

        val todos = model.listRow
        System.out.println(todos)

        System.out.println(rowsRepository.getTitle())
        System.out.println(model.title)

        assertNotNull(todos)
    }

    @Test
    fun sendResultToUI() {
        val foo = MutableLiveData<Resource<List<Rows>>>()
        System.out.print(foo)
        val model = MainViewModel(rowsRepository)
        Mockito.`when`(rowsRepository.loadRows()).thenReturn(foo)
        val observer = mock<Observer<Resource<List<Rows>>>>()
        System.out.println(model.listRow)
        model.listRow.observeForever(observer)
        model.reloadRows()
        Mockito.verify(observer, Mockito.never()).onChanged(Mockito.any())

        val repos = arrayListOf<Rows>(
            Rows(1, "Beavers", "desc","http://upload.wikimedia.org/wikipedia/commons"),
            Rows(2, "Transportation", "desc","href")
        )
        val fooValue = Resource.success(repos)

        foo.value = fooValue
        Mockito.verify(observer).onChanged(fooValue)
    }


}