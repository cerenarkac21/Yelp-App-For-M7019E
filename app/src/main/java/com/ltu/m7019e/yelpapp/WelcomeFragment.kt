package com.ltu.m7019e.yelpapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userCurrentLocationEditText = view.findViewById<EditText>(R.id.userCurrentLocation)

        val userNameEditText = view.findViewById<EditText>(R.id.userName)
        val exploreButton = view.findViewById<Button>(R.id.startButton)

        exploreButton.setOnClickListener {
            val userName = userNameEditText.text.toString()
            val currentLocation = userCurrentLocationEditText.text.toString()

            val action = WelcomeFragmentDirections.actionFirstFragmentToSecondFragment(userName, currentLocation)
            findNavController().navigate(action)
        }
    }
}