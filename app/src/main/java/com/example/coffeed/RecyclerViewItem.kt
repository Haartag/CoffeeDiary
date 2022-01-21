package com.example.coffeed

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class RecyclerViewItem(
    val name: String? = null,
    val manufacturer: String? = null,
    val photoUri: String? = null,
    val rating: Int? = null
) : AbstractItem<RecyclerViewItem.ViewHolder>() {

    override val type: Int
        get() = R.id.fastadapter_item_id
    override val layoutRes: Int
        get() = R.layout.item_card

    override fun getViewHolder(v: View): RecyclerViewItem.ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<RecyclerViewItem>(view) {
        //ToDo add some ViewBinding
        var name: TextView = view.findViewById(R.id.coffeeCardName)
        var manufacturer: TextView = view.findViewById(R.id.coffeeCardManufacturer)
        var photo: ImageView = view.findViewById(R.id.coffeeCardPhoto)
        var rating: TextView = view.findViewById(R.id.coffeeCardRatingTemp)

        override fun bindView(item: RecyclerViewItem, payloads: List<Any>) {
            name.text = item.name
            manufacturer.text = item.manufacturer
            photo.setImageURI(Uri.parse(item.photoUri))
            rating.text = item.rating.toString()
        }

        override fun unbindView(item: RecyclerViewItem) {
            name.text = null
            manufacturer.text = null
            photo.setImageDrawable(null)
            rating.text = null
        }

    }
}