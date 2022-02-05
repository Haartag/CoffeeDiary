package com.example.coffeed

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.example.coffeed.data.DetailsItemCard
import com.example.coffeed.data.ItemCard
import com.example.coffeed.databinding.FragmentInputDescription2Binding
import com.example.coffeed.mainDatabase.CoffeeDatabase
import com.example.coffeed.mainDatabase.CoffeeItem
import com.example.coffeed.mainDatabase.DetailedItem
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
            Log.d(TAG, "onViewCreated: ${args.mainUid} ToNext")
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

            /**
             * PROBLEM HERE!!!
             */
            //findNavController().navigate(R.id.action_inputDetailsFragment_to_detailedItemFragment)

        }
    }
}