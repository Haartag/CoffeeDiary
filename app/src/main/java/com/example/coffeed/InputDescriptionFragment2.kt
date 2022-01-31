package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.database.CoffeeDatabase
import com.example.coffeed.database.CoffeeItem
import com.example.coffeed.databinding.FragmentInputDescription2Binding
import com.example.coffeed.databinding.FragmentInputDescriptionBinding
import kotlinx.coroutines.launch

class InputDescriptionFragment2 : Fragment(R.layout.fragment_input_description_2) {

    private var fragmentInputDescription2Binding: FragmentInputDescription2Binding? = null
    private val args: InputDescriptionFragment2Args by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInputDescription2Binding.bind(view)
        fragmentInputDescription2Binding = binding


        binding.submitButton.setOnClickListener {
            val coffeeItemCard = ItemCard(
                coffeePhoto = args.halfItem.coffeePhoto,
                name = args.halfItem.name,
                manufacturer = args.halfItem.manufacturer,
                type = args.halfItem.type,
                rating = args.halfItem.rating,
                shortDescription = binding.shortDescriptionEditText.text.toString(),
                longDescription = binding.longDescriptionEditText.text.toString()
            )
            //Place item in database, if additional data canceled
            val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
            val input = CoffeeItem(
                0,
                coffeeItemCard.coffeePhoto,
                coffeeItemCard.name,
                coffeeItemCard.manufacturer,
                coffeeItemCard.type,
                coffeeItemCard.rating,
                coffeeItemCard.shortDescription,
                coffeeItemCard.longDescription,
            )
            lifecycleScope.launch { db.coffeeDao.add(input) }
            findNavController().navigate(R.id.action_inputDescriptionFragment2_to_mainScreenFragment)

        }
    }

}