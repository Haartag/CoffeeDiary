package com.example.coffeed

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.coffeed.adapters.RecyclerViewItem
import com.example.coffeed.data.PreviewItemCard
import com.example.coffeed.mainDatabase.CoffeeDatabase
import com.example.coffeed.databinding.FragmentMainScreenBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.coroutines.launch
import java.io.File

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var fragmentMainScreenBinding: FragmentMainScreenBinding? = null
    private val items = mutableListOf<RecyclerViewItem>()

    //fastAdapter
    private val itemAdapter = ItemAdapter<RecyclerViewItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

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

        lifecycleScope.launch {
            //If there is something in DB, take information for RecyclerView and make it.
            if (db.coffeeDao.countType() > 0) {
                val mainList = db.coffeeDao.getAllPreviewItems()
                //clean ITEMS to prevent doubling on back from another fragment
                items.clear()
                mainList.forEach { items.add(it.toRecyclerViewItem()) }

                items.sortBy { items -> items.rating }
                items.reverse()

                itemAdapter.clear()
                itemAdapter.add(items)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = fastAdapter

            }
        }
        //OnClick - go to ItemFragment
        fastAdapter.onClickListener = { view, adapter, item, position ->
            val action =
                MainScreenFragmentDirections.actionMainScreenFragmentToItemFragment(item.uid ?: 1)
            findNavController().navigate(action)
            true
        }
        //onLongClick - inflate item menu
        fastAdapter.onLongClickListener = { view, adapter, item, position ->
            showPopupMenu(view, item.uid!!)
            true
        }
    }

    override fun onDestroyView() {
        fragmentMainScreenBinding = null
        super.onDestroyView()
    }

    //convert ItemCard in RecyclerViewItem ToDo: change that
    private fun PreviewItemCard.toRecyclerViewItem() = RecyclerViewItem(
        name = name,
        manufacturer = manufacturer,
        photoUri = coffeePhoto,
        rating = rating,
        brewType = type,
        uid = uid
    )

    private fun showPopupMenu(view: View, uid: Int) {
        val popup = PopupMenu(context, view)
        popup.inflate(R.menu.recycler_view_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.menu_recycler_change -> {
                    Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.menu_recycler_delete -> {
                    lifecycleScope.launch { deleteItem(uid) }
                }
            }
            true
        })
        popup.show()
    }

    private suspend fun deleteItem(uid: Int) {
        //delete item from database
        val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
        db.coffeeDao.deleteItemById(uid)
        //delete photo from storage
        val itemToDelete = items.filter { RecyclerViewItem -> RecyclerViewItem.uid == uid }[0]
        val fileToDelete = File(itemToDelete.photoUri!!)
        if (fileToDelete.exists() && itemToDelete.photoUri != "android.resource://com.example.coffeed/drawable/coffee_photo") {
            fileToDelete.delete()
        }
        //delete item from RecyclerView adapter
        items.removeIf { RecyclerViewItem -> RecyclerViewItem.uid == uid }
        itemAdapter.clear()
        itemAdapter.add(items)


    }


}