package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.databinding.FragmentInputDescriptionBinding

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

        binding.submitButton.setOnClickListener {
            val coffeeItemCard = ItemCard(
                coffeePhoto = args.photoUri,
                name = binding.nameOfCoffeeEditText.text.toString(),
                manufacturer = binding.manufacturerOfCoffeeEditText.text.toString(),
                type = binding.coffeeTypeSpinner.selectedItem.toString(),
                shortDescription = binding.shortDescriptionEditText.text.toString(),
                longDescription = binding.longDescriptionEditText.text.toString()
            )
        }


    }

    override fun onDestroyView() {
        fragmentInputDescriptionBinding = null
        super.onDestroyView()
    }
}