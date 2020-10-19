package ru.uomkri.marlerinotest.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.uomkri.marlerinotest.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var args: DetailsFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        args = DetailsFragmentArgs.fromBundle(requireArguments())

        viewModel.getItem(args.position)

        viewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.itemTitle.text = it.title
                binding.itemDescription.text = it.description
                binding.itemPubDate.text = it.pubDate

                Picasso.get()
                    .load(it.enclosureUrl!!.url)
                    .into(binding.imageView)
            }
        })

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        viewModel.clearItem()
    }
}