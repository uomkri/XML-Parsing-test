package ru.uomkri.marlerinotest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.uomkri.marlerinotest.R
import ru.uomkri.marlerinotest.repo.net.RssItem

class ListAdapter(data: List<RssItem>) : RecyclerView.Adapter<ViewHolder>() {

    private val mutableData = data.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mutableData[position]

        holder.title.text = item.title.take(40) + "..."
    }

    fun refreshList(list: List<RssItem>) {
        mutableData.clear()
        mutableData.addAll(list)
        notifyDataSetChanged()
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.itemName)
}