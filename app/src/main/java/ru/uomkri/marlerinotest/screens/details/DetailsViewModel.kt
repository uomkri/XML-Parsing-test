package ru.uomkri.marlerinotest.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.uomkri.marlerinotest.repo.Repository
import ru.uomkri.marlerinotest.repo.net.RssItem

class DetailsViewModel : ViewModel() {

    private val rssFeed = Repository.rssFeed

    private val _selectedItem = MutableLiveData<RssItem>()
    val selectedItem: LiveData<RssItem>
        get() = _selectedItem

    fun getItem(position: Int) {
        _selectedItem.postValue(rssFeed.value!![position])
    }

    fun clearItem() {
        _selectedItem.value = null
    }
}