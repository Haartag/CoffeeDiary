package com.example.coffeed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.coffeed.database.CoffeeDatabase
import com.example.coffeed.database.CoffeeItem
import com.example.coffeed.databinding.FragmentMainScreenBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var fragmentMainScreenBinding: FragmentMainScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = Slide()
        exitTransition = Slide()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainScreenBinding.bind(view)
        fragmentMainScreenBinding = binding
        val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)

        //fastAdapter
        val itemAdapter = ItemAdapter<RecyclerViewItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        //If there is something in DB, take information for RecyclerView and make it.
        if (db.coffeeDao.countType() > 0) {
            val mainList = mutableListOf<PreviewItemCard>()
            for (i in 1..db.coffeeDao.countType()) {
                mainList.add(db.coffeeDao.getPreviewItemById(i))
            }
            val ITEMS = mutableListOf<RecyclerViewItem>()
            mainList.forEach { ITEMS.add(it.toRecyclerViewItem()) }
            itemAdapter.add(ITEMS)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = fastAdapter
        }
    }

    override fun onDestroyView() {
        fragmentMainScreenBinding = null
        super.onDestroyView()
    }

    private fun PreviewItemCard.toRecyclerViewItem() = RecyclerViewItem(
        name = name,
        manufacturer = manufacturer,
        photoUri = coffeePhoto,
        rating = rating
    )


}