package com.example.coffeed

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class RecyclerViewItem(
    val name: String? = null,
    val manufacturer: String? = null,
    val photoUri: String? = null,
    val rating: Float? = null,
    val brewType: String? = null,
    val uid: Int? = null
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
        var rating: RatingBar = view.findViewById(R.id.itemRatingBar)
        var brewType: ImageView = view.findViewById(R.id.brewType)

        override fun bindView(item: RecyclerViewItem, payloads: List<Any>) {
            name.text = item.name
            manufacturer.text = item.manufacturer
            Glide.with(photo.context)
                .load(Uri.parse(item.photoUri))
                //.sizeMultiplier(0.5F)
                //.thumbnail(0.05F)
                .into(photo)
            rating.rating = item.rating ?: 0F
            when (item.brewType) {
                "Espresso" -> brewType.setImageResource(R.drawable.ic_espresso)
                "Pourover" -> brewType.setImageResource(R.drawable.ic_pourover)
                "Dzezva" -> brewType.setImageResource(R.drawable.ic_jezva)
                "Moka" -> brewType.setImageResource(R.drawable.coffee_placeholder)
            }

        }

        override fun unbindView(item: RecyclerViewItem) {
            name.text = null
            manufacturer.text = null
            photo.setImageDrawable(null)
            //rating.rating = null
        }

    }
}