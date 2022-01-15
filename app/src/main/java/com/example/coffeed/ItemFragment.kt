package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.example.coffeed.databinding.FragmentItemBinding


class ItemFragment : Fragment(R.layout.fragment_item) {

    private var fragmentItem: FragmentItemBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentItemBinding.bind(view)
        fragmentItem = binding
    }

    override fun onDestroyView() {
        fragmentItem = null
        super.onDestroyView()
    }
}