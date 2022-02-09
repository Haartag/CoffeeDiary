package com.example.coffeed.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.coffeed.R

/**
 * Adapter for custom Spinner, InputDescriptionFragment
 */

internal class SpinnerAdapter(context: Context, states: List<String>) :
    ArrayAdapter<String?>(context, R.layout.spinner_selected_item, states) {

    var brewTypesList: List<String> = states

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        return getCustomView(position, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    // return custom View
    private fun getCustomView(position: Int, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row: View = inflater.inflate(R.layout.item_spinner, parent, false)

        val brewType: TextView = row.findViewById(R.id.text)
        val brewIcon: ImageView = row.findViewById(R.id.img)

        val iconAddress = when (brewTypesList[position]) {
            getBrewTypeName(0) -> (R.drawable.ic_espresso)
            getBrewTypeName(1) -> (R.drawable.ic_pourover)
            getBrewTypeName(2) -> (R.drawable.ic_jezva)
            getBrewTypeName(3) -> (R.drawable.ic_moka)
            else -> (R.drawable.coffee_placeholder)
        }

        val res = ContextCompat.getDrawable(context, iconAddress)

        brewType.text = brewTypesList[position]
        brewIcon.setImageDrawable(res)
        return row
    }
    //take brew type, for localization
    private fun getBrewTypeName(position: Int): String {
        return context.resources.getStringArray(R.array.coffeeTypes)[position]
    }

}