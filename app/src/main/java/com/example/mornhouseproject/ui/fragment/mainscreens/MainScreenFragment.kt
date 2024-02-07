package com.example.mornhouseproject.ui.fragment.mainscreens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mornhouse.test.project.databinding.FragmentMainBinding
import com.example.mornhouseproject.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val mainScreenAdapter by lazy { MainScreenAdapter(::navigateToSecondScreen) }
    private val viewModel: MainViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFactToAdapterList()
        initRv()
        checkInput()
        getRandomFact()
    }

    private fun checkInput() {
        val number = binding.numberEditText.text
        binding.factBtn.setOnClickListener {
            if (number.isNotEmpty()) {
                checkStringLenth(number.toString())
            } else Toast.makeText(requireContext(), "Write number ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkStringLenth(string: String) {
        if (string.length > 8) {
            Toast.makeText(requireContext(), "To long number  ", Toast.LENGTH_SHORT).show()
        } else {
            getNumberFact(string.toInt())
            binding.numberEditText.text.clear()
        }
    }

    private fun addFactToAdapterList() {
        viewModel.fact.observe(viewLifecycleOwner) { numberFactList ->
            mainScreenAdapter.submitList(numberFactList)
            mainScreenAdapter.scrollToPosition(numberFactList.size)
        }
    }

    private fun getNumberFact(number: Int) {
        viewModel.getNumberFact(number)
    }

    private fun getRandomFact() {
        binding.randomBtn.setOnClickListener {
            viewModel.getRandomFact()
        }
    }

    private fun initRv() = binding.factRecycler.apply {
        adapter = mainScreenAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun navigateToSecondScreen(factId: Long) {
        val action = MainScreenFragmentDirections.actionMainFragmentToDescriptionFragment(factId)
        findNavController().navigate(action)
    }

}


