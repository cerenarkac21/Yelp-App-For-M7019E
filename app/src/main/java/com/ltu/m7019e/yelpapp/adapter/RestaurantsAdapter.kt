package com.ltu.m7019e.yelpapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ltu.m7019e.yelpapp.R
import com.ltu.m7019e.yelpapp.model.YelpRestaurant

/*
class RestaurantsAdapter(private val restaurantListClickListener: RestaurantListClickListener) : ListAdapter<YelpRestaurant, RestaurantsAdapter.ViewHolder>(RestaurantsListDiffCallback()){

    class ViewHolder(private var binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: YelpRestaurant, movieClickListener: RestaurantListClickListener) {
            binding.movie = movie
            binding.clickListener = restaurantClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), restaurantClickListener)
    }
}
}


class RestaurantsListDiffCallback : DiffUtil.ItemCallback<YelpRestaurant>() {
    override fun areItemsTheSame(oldItem: YelpRestaurant, newItem: YelpRestaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: YelpRestaurant, newItem: YelpRestaurant): Boolean {
        return oldItem == newItem
    }

}

class RestaurantListClickListener(val clickListener: (restaurant: YelpRestaurant) -> Unit) {
    fun onClick(restaurant: YelpRestaurant) = clickListener(restaurant)
}

 */

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }
    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        private val tvNumReviews = itemView.findViewById<TextView>(R.id.tvNumReviews)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        private val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
        private val tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        fun bind(restaurant: YelpRestaurant) {
            tvName.text = restaurant.name
            ratingBar.rating = restaurant.rating.toFloat()
            tvNumReviews.text = "${restaurant.numOfReviews} reviews"
            tvAddress.text = restaurant.location.address
            tvCategory.text = restaurant.categories[0].title
            tvDistance.text = restaurant.displayDistance()
            tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(20))).into(imageView)
        }
    }

}