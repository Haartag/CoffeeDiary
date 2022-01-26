package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.database.CoffeeDatabase
import com.example.coffeed.database.CoffeeItem
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

        //Make half ItemCard from collected data, then transfer it to next Fragment
        binding.submitButton.setOnClickListener {
            val coffeeItemCard = ItemCard(
                coffeePhoto = args.photoUri,
                name = binding.nameOfCoffeeEditText.text.toString(),
                manufacturer = binding.manufacturerOfCoffeeEditText.text.toString(),
                type = binding.coffeeTypeSpinner.selectedItem.toString(),
                rating = binding.ratingBar.rating,
                shortDescription = "",//binding.shortDescriptionEditText.text.toString(),
                longDescription = ""//binding.longDescriptionEditText.text.toString()
            )
            val action = InputDescriptionFragmentDirections.actionInputDescriptionFragmentToInputDescriptionFragment2(coffeeItemCard)
            findNavController().navigate(action)
        }


    }

    override fun onDestroyView() {
        fragmentInputDescriptionBinding = null
        super.onDestroyView()
    }
}