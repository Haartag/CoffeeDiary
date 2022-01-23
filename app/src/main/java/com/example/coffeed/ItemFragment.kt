package com.example.coffeed

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import com.bumptech.glide.Glide
import com.example.coffeed.database.CoffeeDatabase
import com.example.coffeed.databinding.FragmentItemBinding


class ItemFragment : Fragment(R.layout.fragment_item) {

    private var fragmentItemBinding: FragmentItemBinding? = null
    private val args: ItemFragmentArgs by navArgs()

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
        val itemCard = db.coffeeDao.getItemById(args.uid)

        //set data to fields
        Glide.with(binding.coffeePhoto.context)
            .load(Uri.parse(itemCard.coffeePhoto))
            .into(binding.coffeePhoto)
        binding.nameOfCoffeeText.text = itemCard.name
        binding.manufacturerOfCoffeeText.text = itemCard.manufacturer
        binding.shortDescriptionText.text = itemCard.shortDescription
        binding.longDescriptionText.text = itemCard.longDescription
    }

    override fun onDestroyView() {
        fragmentItemBinding = null
        super.onDestroyView()
    }
}