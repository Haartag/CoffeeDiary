package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.example.coffeed.databinding.FragmentDetailedItemBinding

class DetailedItemFragment : Fragment(R.layout.fragment_detailed_item) {

    private var fragmentDetailedItemBinding: FragmentDetailedItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailedItemBinding.bind(view)
        fragmentDetailedItemBinding = binding
    }

    override fun onDestroyView() {
        fragmentDetailedItemBinding = null
        super.onDestroyView()
    }

}