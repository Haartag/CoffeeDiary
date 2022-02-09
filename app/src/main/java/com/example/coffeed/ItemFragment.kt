package com.example.coffeed

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.bumptech.glide.Glide
import com.example.coffeed.data.ItemCard
import com.example.coffeed.databinding.FragmentItemBinding
import com.example.coffeed.mainDatabase.CoffeeDatabase
import kotlinx.coroutines.launch


class ItemFragment : Fragment(R.layout.fragment_item) {

    private var fragmentItemBinding: FragmentItemBinding? = null
    private val args: ItemFragmentArgs by navArgs()
    private lateinit var itemCard: ItemCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentItemBinding.bind(view)
        fragmentItemBinding = binding

        //take item card from database
        val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
        lifecycleScope.launch {
            itemCard = db.coffeeDao.getItemById(args.uid)

            //set data to fields
            Glide.with(binding.coffeePhoto.context)
                .load(Uri.parse(itemCard.coffeePhoto))
                .into(binding.coffeePhoto)
            binding.nameOfCoffeeText.text = itemCard.name
            binding.manufacturerOfCoffeeText.text = itemCard.manufacturer
            binding.descriptionText.text = itemCard.description
            binding.cardRatingBar.rating = itemCard.rating
        }

        binding.detailedInformationButtonText.setOnClickListener {
            val action =
                ItemFragmentDirections.actionItemFragmentToDetailedItemFragment(itemCard.uid)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        fragmentItemBinding = null
        super.onDestroyView()
    }
}