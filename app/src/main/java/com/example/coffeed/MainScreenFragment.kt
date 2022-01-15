package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.transition.Slide
import com.example.coffeed.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var fragmentMainScreenBinding: FragmentMainScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainScreenBinding.bind(view)
        fragmentMainScreenBinding = binding

        binding.button2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.itemFragment))

        //val label = findNavController().currentDestination
    }

    override fun onDestroyView() {
        fragmentMainScreenBinding = null
        super.onDestroyView()
    }



}