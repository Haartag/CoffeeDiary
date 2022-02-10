package com.valerytimofeev.coffeed.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valerytimofeev.coffeed.R
import com.valerytimofeev.coffeed.data.DetailsItemCard


/**
 * Adapter for ViewPager, DetailedItemFragment.
 */

class PagerAdapter(
    private val input: List<DetailsItemCard>,
    private val clickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.detailed_page, parent, false)
        ) {
                clickListener(input[it].uid)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (input[position].uid == -1) {
            holder.coffeeName.setText(R.string.add_new)
            Glide.with(holder.image.context)
                .load(R.drawable.addnew_photo)
                .into(holder.image)

        } else {
            holder.coffeeName.text = input[position].coffeeName

            Glide.with(holder.image.context)
                .load(Uri.parse(input[position].detailedPhoto))
                .into(holder.image)

            holder.description.text = input[position].detailedText
        }


    }

    override fun getItemCount() = input.size
}

class MyViewHolder(view: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    val coffeeName: TextView = view.findViewById(R.id.detailedTitleText)
    val description: TextView = view.findViewById(R.id.detailedDescriptionText)
    val image: ImageView = view.findViewById(R.id.detailedImage)

    init {
        view.setOnClickListener {
            clickAtPosition(bindingAdapterPosition)
        }
    }
}