package ru.uomkri.marlerinotest.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.uomkri.marlerinotest.repo.Repository

class ListViewModel : ViewModel() {

    val rssFeed = Repository.rssFeed

    fun fetchRssFeed() {
        viewModelScope.launch {
            Repository.getRssFeed()
        }
    }
}