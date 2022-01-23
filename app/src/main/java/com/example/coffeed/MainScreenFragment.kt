package com.example.coffeed

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import com.example.coffeed.database.CoffeeDatabase
import com.example.coffeed.database.CoffeeItem
import com.example.coffeed.databinding.FragmentMainScreenBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private var fragmentMainScreenBinding: FragmentMainScreenBinding? = null
    private val ITEMS = mutableListOf<RecyclerViewItem>()
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


        //If there is something in DB, take information for RecyclerView and make it.
        if (db.coffeeDao.countType() > 0) {
            val mainList = db.coffeeDao.getAllPreviewItems()
            //val ITEMS = mutableListOf<RecyclerViewItem>()
            mainList.forEach { ITEMS.add(it.toRecyclerViewItem()) }
            itemAdapter.add(ITEMS)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = fastAdapter
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
            Toast.makeText(activity, "$position, $item", Toast.LENGTH_SHORT).show()
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
                    deleteItem(uid)
                }
            }
            true
        })
        popup.show()
    }

    private fun deleteItem(uid: Int) {
        val db = CoffeeDatabase.getInstance(requireActivity().applicationContext)
        db.coffeeDao.deleteItemById(uid)
        ITEMS.removeIf{RecyclerViewItem -> RecyclerViewItem.uid == uid}
        itemAdapter.clear()
        itemAdapter.add(ITEMS)
    }


}