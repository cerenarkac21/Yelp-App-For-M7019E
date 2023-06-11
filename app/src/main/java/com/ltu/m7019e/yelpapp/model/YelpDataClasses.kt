package com.ltu.m7019e.yelpapp.model


import com.squareup.moshi.Json

data class YelpSearchResult(
    @Json(name = "businesses") val restaurants: List<YelpRestaurant>,
    val total: String,
    val region: YelpRegion
)
data class YelpRegion(
    val center: YelpCenter
)

data class YelpCenter(
    val latitude: String,
    val longitude: String
)
data class YelpRestaurant (
    val id: String,
    @Json(name = "alias") val restaurantAlias: String,
    val name: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "is_closed") val isClosed: Boolean,
    @Json(name = "url") val businessUrl: String,
    @Json(name = "review_count") val numOfReviews: String,
    val categories: List<YelpCategory>,
    val rating: Double,
    val coordinates: YelpCoordinates,
    val transactions: List<String>,
    val price: String?,
    val location: YelpLocation,
    val phone: String,
    @Json(name = "display_phone") val displayPhone: String,
    @Json(name = "distance") val distanceInMeters: String
){
    fun displayDistance(): String {
        val milesPerMeter = 0.000621371
        val distanceInMetersNumeric = distanceInMeters.toDoubleOrNull()
        if (distanceInMetersNumeric != null) {
            val distanceInMiles = "%.2f".format(distanceInMetersNumeric * milesPerMeter)
            return "$distanceInMiles mi"
        } else {
            return "Invalid distance"
        }
    }

}

data class YelpCoordinates(
    val latitude: String,
    val longitude: String
)


data class YelpCategory(
    val alias: String,
    val title: String
)

data class YelpLocation(
    @Json(name = "address1") val address: String,
    @Json(name = "display_address") val displayAddress: List<String>
)