package ru.uomkri.marlerinotest.screens.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.uomkri.marlerinotest.databinding.FragmentListBinding
import ru.uomkri.marlerinotest.utils.ListAdapter
import ru.uomkri.marlerinotest.utils.RecyclerItemClickListener

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel.fetchRssFeed()

        val listLayoutManager = LinearLayoutManager(context)
        val divider = DividerItemDecoration(context, listLayoutManager.orientation)


        viewModel.rssFeed.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e("list", it.toString())

                listAdapter = ListAdapter(it)

                binding.recyclerView.apply {
                    layoutManager = listLayoutManager
                    adapter = listAdapter
                    addItemDecoration(divider)
                }
            }
        })

        binding.recyclerView.addOnItemTouchListener(RecyclerItemClickListener(binding.recyclerView,
            object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    requireView().findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(position))
                }
            }
        ))

        binding.privacyPolicy.setOnClickListener {
            requireView().findNavController().navigate(ListFragmentDirections.actionListFragmentToWebFragment())
        }

        return binding.root
    }
}