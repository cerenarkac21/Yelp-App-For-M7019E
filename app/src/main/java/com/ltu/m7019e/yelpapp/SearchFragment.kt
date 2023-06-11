package com.ltu.m7019e.yelpapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SearchFragment : Fragment() {
    private lateinit var userName: String
    private lateinit var currentLocation: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userName = SearchFragmentArgs.fromBundle(requireArguments()).userName
        currentLocation = SearchFragmentArgs.fromBundle(requireArguments()).currentLocation

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton = view.findViewById<Button>(R.id.searchButton)

        val searchTermEditText = view.findViewById<EditText>(R.id.searchTermEditText)
        val searchLocationEditText = view.findViewById<EditText>(R.id.searchLocationEditText)

        val tvSearch = view.findViewById<TextView>(R.id.tvSearch)

        // Set the text of tvSearch with the userName
        if(userName != ""){
            tvSearch.text = "What are you looking for, $userName?"
        }
        else{
            tvSearch.text = "What are you looking for?"
        }

        currentLocation?.let {
            searchLocationEditText.setText(it)
        }

        searchButton.setOnClickListener {
            val searchTerm = searchTermEditText.text.toString()
            val location = searchLocationEditText.text.toString()

            // Pass the search term and location to the next fragment
            val action = SearchFragmentDirections.actionSecondFragmentToThirdFragment(searchTerm, location)
            findNavController().navigate(action)
        }
    }

}