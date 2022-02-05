package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.data.ItemCard
import com.example.coffeed.mainDatabase.CoffeeDatabase
import com.example.coffeed.mainDatabase.CoffeeItem
import com.example.coffeed.databinding.FragmentInputDescription2Binding
import com.example.coffeed.mainDatabase.DetailedItem
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
                description = binding.shortDescriptionEditText.text.toString()
            )
            //Place item in database
            val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
            val input = CoffeeItem(
                0,
                coffeeItemCard.coffeePhoto,
                coffeeItemCard.name,
                coffeeItemCard.manufacturer,
                coffeeItemCard.type,
                coffeeItemCard.rating,
                coffeeItemCard.description
            )
            lifecycleScope.launch { db.coffeeDao.add(input) }
            findNavController().navigate(R.id.action_inputDescriptionFragment2_to_mainScreenFragment)

        }
    }

}