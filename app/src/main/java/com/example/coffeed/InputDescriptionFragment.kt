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

        //Make ItemCard from collected data, then transfer it to next Fragment
        binding.submitButton.setOnClickListener {
            val coffeeItemCard = ItemCard(
                coffeePhoto = args.photoUri,
                name = binding.nameOfCoffeeEditText.text.toString(),
                manufacturer = binding.manufacturerOfCoffeeEditText.text.toString(),
                type = binding.coffeeTypeSpinner.selectedItem.toString(),
                shortDescription = binding.shortDescriptionEditText.text.toString(),
                longDescription = binding.longDescriptionEditText.text.toString()
            )
            //Place item in database
            val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
            val input = CoffeeItem(
                0,
                coffeeItemCard.coffeePhoto.toString(),
                coffeeItemCard.name,
                coffeeItemCard.manufacturer,
                coffeeItemCard.type,
                2,
                coffeeItemCard.shortDescription,
                coffeeItemCard.longDescription,
            )
            db.coffeeDao.add(input)
            findNavController().navigate(R.id.action_inputDescriptionFragment_to_mainScreenFragment)
        }


    }

    override fun onDestroyView() {
        fragmentInputDescriptionBinding = null
        super.onDestroyView()
    }
}