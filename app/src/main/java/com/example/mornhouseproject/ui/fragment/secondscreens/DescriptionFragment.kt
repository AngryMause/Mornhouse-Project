package com.example.mornhouseproject.ui.fragment.secondscreens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mornhouse.test.project.databinding.FragmentSecondBinding
import com.example.mornhouseproject.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {
    private val args by navArgs<DescriptionFragmentArgs>()
    private val viewModel: SecondFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDescriptionById()
        gotToMainScreen()
    }

    private fun showDescriptionById() {
        viewModel.getDescriptionById(args.factId)
        viewModel.fact.observe(viewLifecycleOwner) {
            binding.factTv.text = it.fact
            binding.number.text = it.number
        }
    }


    private fun gotToMainScreen() {
        activity?.onBackPressedDispatcher?.addCallback {
            isEnabled = true
            findNavController().popBackStack()
        }
    }
}