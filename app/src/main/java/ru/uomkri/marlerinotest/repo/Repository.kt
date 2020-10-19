package ru.uomkri.marlerinotest.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.uomkri.marlerinotest.repo.net.RssItem
import ru.uomkri.marlerinotest.repo.net.RssResource

object Repository {
    private val _rssFeed = MutableLiveData<List<RssItem>>()
    val rssFeed: LiveData<List<RssItem>>
        get() = _rssFeed

    suspend fun getRssFeed() {
        withContext(Dispatchers.IO) {
            val result = RssResource.retrofitService.getRssFeed().await()

            if (result.channel != null) {
                _rssFeed.postValue(result.channel!!.itemList)
            }
        }
    }

}