package com.dzhunet.hasan.autoscout24_assignment.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzhunet.hasan.autoscout24_assignment.R
import com.dzhunet.hasan.autoscout24_assignment.data.network.responses.FeedResultModel
import com.dzhunet.hasan.autoscout24_assignment.data.vo.Resource
import com.dzhunet.hasan.autoscout24_assignment.databinding.FragmentFeedsBinding
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.adapter.FeedListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedsFragment : Fragment() {

    private lateinit var binding: FragmentFeedsBinding

    private val feedListAdapter: FeedListAdapter = FeedListAdapter { feedItem ->
        feedItem.id?.let { vehicleId ->
            viewModel.getVehicleNotes(vehicleId = vehicleId)?.let { notes ->
                if (notes.isNotEmpty()) {
                    AlertDialog.Builder(requireActivity()).setTitle(R.string.vehicle_notes_title)
                        .setItems(
                            notes
                        ) { _, _ ->
                        }.setPositiveButton(
                            android.R.string.ok
                        ) { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        }
    }

    private val viewModel: FeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.feedList.apply {
            adapter = feedListAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getFeeds.observe(viewLifecycleOwner, Observer {
            handleResult(it)
        })
    }

    private fun handleResult(result: Resource<MutableList<FeedResultModel>>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                binding.main.showContent()
                feedListAdapter.submitList(result.data)
            }
            Resource.Status.ERROR -> {
                binding.main.showEmpty(
                    R.drawable.ic_baseline_error_24,
                    getString(R.string.error_generic_title),
                    getString(R.string.error_generic_description, result.message)
                )
            }
            Resource.Status.LOADING -> {
                binding.main.showLoading()
            }
        }
    }

}