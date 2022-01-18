package com.example.coffeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.databinding.FragmentInputDescriptionBinding
import com.example.coffeed.databinding.FragmentItemBinding

class InputDescriptionFragment : Fragment(R.layout.fragment_input_description) {

    private var fragmentInputDescriptionBinding: FragmentInputDescriptionBinding? = null
    private val args: InputDescriptionFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInputDescriptionBinding.bind(view)
        fragmentInputDescriptionBinding = binding
        binding.imageView.setImageURI(args.photoUri)

    }

    override fun onDestroyView() {
        fragmentInputDescriptionBinding = null
        super.onDestroyView()
    }
}