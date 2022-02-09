package com.example.coffeed

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Slide
import androidx.viewpager2.widget.ViewPager2
import com.example.coffeed.adapters.PagerAdapter
import com.example.coffeed.data.DetailsItemCard
import com.example.coffeed.databinding.FragmentDetailedItemBinding
import com.example.coffeed.mainDatabase.CoffeeDatabase
import com.example.coffeed.mainDatabase.DetailedItem
import kotlinx.coroutines.launch

class DetailedItemFragment : Fragment(R.layout.fragment_detailed_item) {

    private var fragmentDetailedItemBinding: FragmentDetailedItemBinding? = null
    private val args: DetailedItemFragmentArgs by navArgs()

    private val details = mutableListOf<DetailsItemCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailedItemBinding.bind(view)
        fragmentDetailedItemBinding = binding

        val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)

        //add first card with add button
        details.add(DetailsItemCard(-1, "", "addNew", "", -1))

        //take cards from database
        lifecycleScope.launch {
            val testOut = db.coffeeDao.getDetailedItems(args.mainUid)

            testOut.forEach { it ->
                val coffeeName = it.coffeeItem.name
                it.detailedItems.forEach {
                    details.add(
                        DetailsItemCard(
                            0,
                            coffeeName,
                            it.detailedPhoto,
                            it.detailedText,
                            it.coffeeId
                        )
                    )
                }
            }
            binding.viewPager.adapter = PagerAdapter(details) {
                if (it == -1) {
                    val action = DetailedItemFragmentDirections.actionDetailedItemFragmentToCoffeePhotoFragment(1, args.mainUid)
                    findNavController().navigate(action)
                }
            }
            if (details.size > 1) {
                binding.viewPager.currentItem = 1
            }
        }


    }

    override fun onDestroyView() {
        fragmentDetailedItemBinding = null
        super.onDestroyView()
    }

}