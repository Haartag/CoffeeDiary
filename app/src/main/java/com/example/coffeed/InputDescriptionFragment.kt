package com.example.coffeed

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.adapters.SpinnerAdapter
import com.example.coffeed.data.ItemCard
import com.example.coffeed.databinding.FragmentInputDescriptionBinding

class InputDescriptionFragment : Fragment(R.layout.fragment_input_description) {

    private var fragmentInputDescriptionBinding: FragmentInputDescriptionBinding? = null
    private val args: InputDescriptionFragmentArgs by navArgs()
    private lateinit var selectedBrewType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInputDescriptionBinding.bind(view)
        fragmentInputDescriptionBinding = binding

        //set spinner icons
        val brewTypes: List<String> =
            ArrayList(listOf(*resources.getStringArray(R.array.coffeeTypes)))
        val adapter = SpinnerAdapter(requireContext(), brewTypes)
        adapter.setDropDownViewResource(R.layout.item_spinner)
        binding.coffeeTypeSpinner.adapter = adapter

        binding.coffeeTypeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    i: Int,
                    l: Long
                ) {
                    selectedBrewType = brewTypes[i]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        //Make half ItemCard from collected data, then transfer it to next Fragment
        binding.submitButton.setOnClickListener {
            val coffeeItemCard = ItemCard(
                coffeePhoto = args.photoUri,
                name = binding.nameOfCoffeeEditText.text.toString(),
                manufacturer = binding.manufacturerOfCoffeeEditText.text.toString(),
                type = selectedBrewType,
                rating = binding.ratingBar.rating,
                description = ""
            )
            val action =
                InputDescriptionFragmentDirections.actionInputDescriptionFragmentToInputDescriptionFragment2(
                    coffeeItemCard
                )
            findNavController().navigate(action)
        }


    }

    override fun onDestroyView() {
        fragmentInputDescriptionBinding = null
        super.onDestroyView()
    }
}