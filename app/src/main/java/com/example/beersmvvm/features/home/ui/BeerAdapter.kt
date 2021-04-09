package com.example.beersmvvm.features.home.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beersmvvm.R
import com.example.beersmvvm.core.extensions.inflate
import com.example.beersmvvm.core.extensions.loadFromUrl
import com.example.beersmvvm.features.home.domain.model.Beer
import dagger.Provides
import kotlinx.android.synthetic.main.item_beer_row.view.*
import javax.inject.Inject
import kotlin.properties.Delegates


class BeerAdapter
    : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {
    internal var collection: List<Beer> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (Beer) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_beer_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(beerView: Beer, clickListener: (Beer) -> Unit) {
            itemView.tvTitleBeer.text = beerView.name
            itemView.tvDescriptionBeer.text = beerView.description
            itemView.imgBeer.loadFromUrl(beerView.image)
            itemView.setOnClickListener { clickListener(beerView) }
        }
    }
}