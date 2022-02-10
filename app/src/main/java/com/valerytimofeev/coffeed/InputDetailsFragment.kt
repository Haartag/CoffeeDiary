package com.valerytimofeev.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.valerytimofeev.coffeed.databinding.FragmentInputDescription2Binding
import com.valerytimofeev.coffeed.mainDatabase.CoffeeDatabase
import com.valerytimofeev.coffeed.mainDatabase.DetailedItem
import kotlinx.coroutines.launch

class InputDetailsFragment : Fragment(R.layout.fragment_input_details) {

    private var fragmentInputDetailsBinding: FragmentInputDescription2Binding? = null
    private val args: InputDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInputDescription2Binding.bind(view)
        fragmentInputDetailsBinding = binding

        //Place item in database
        binding.submitButton.setOnClickListener {
            val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
            val oldUid = args.mainUid
            lifecycleScope.launch {
                val detailedItemCard = DetailedItem(
                    0,
                    args.photoUri,
                    binding.shortDescriptionEditText.text.toString(),
                    oldUid
                )
                db.coffeeDao.add(detailedItemCard)
            }
            val action =
                InputDetailsFragmentDirections.actionInputDetailsFragmentToDetailedItemFragment(args.mainUid)
            findNavController().navigate(action)
        }
    }
}