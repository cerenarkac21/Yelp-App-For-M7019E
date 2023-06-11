package com.ltu.m7019e.yelpapp
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ltu.m7019e.yelpapp.adapter.RestaurantsAdapter
import com.ltu.m7019e.yelpapp.model.YelpRestaurant
import com.ltu.m7019e.yelpapp.model.YelpSearchResult
import com.ltu.m7019e.yelpapp.network.yelpApiService
import com.ltu.m7019e.yelpapp.utils.Constants.YELP_FUSION_API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class RestaurantListFragment : Fragment() {

    private lateinit var searchTerm: String
    private lateinit var location: String

    private val restaurants = mutableListOf<YelpRestaurant>()
    private lateinit var adapter: RestaurantsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_restaurant_list, container, false)
        adapter = RestaurantsAdapter(requireContext(), restaurants)
        val rvRestaurants = view.findViewById<RecyclerView>(R.id.rvRestaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(requireContext())

        // Retrieve the search term and location from arguments
        searchTerm = RestaurantListFragmentArgs.fromBundle(requireArguments()).searchTerm
        location = RestaurantListFragmentArgs.fromBundle(requireArguments()).location
        searchRestaurants(searchTerm, location)
        return view
    }

    private fun searchRestaurants(searchTerm: String, location: String){
        yelpApiService.searchRestaurants("Bearer $YELP_FUSION_API_KEY", searchTerm = searchTerm, location = location).enqueue(object:
            Callback<YelpSearchResult> {
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Timber.tag("ON RESPONSE").d("$response")
                val body = response.body()
                if (body == null) {
                    Timber.tag("on response, null body").w("Did not receive valid response body from Yelp Api")
                    return
                }
                val startIndex = restaurants.size
                restaurants.addAll(body.restaurants)
                val newItemCount = restaurants.size - startIndex
                adapter.notifyItemRangeInserted(startIndex, newItemCount)
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                // Handle the failure
                Timber.tag("ON RESPONSE").d("$t")
            }
        })
    }

}
