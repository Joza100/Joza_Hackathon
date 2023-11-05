package com.josip.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_searchFragment)
        }
        view.findViewById<Button>(R.id.favorites_button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_menuFragment_to_favoritesFragment)
        }
    }
}