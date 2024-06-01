package com.bengisusahin.days_13.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bengisusahin.days_13.R
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    lateinit var homeName : EditText
    lateinit var homeButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeName = view.findViewById(R.id.home_name)
        homeButton = view.findViewById(R.id.home_button)

        homeButton.setOnClickListener {
            val name = homeName.text.toString()
            // view.context yerine requireContext() de olur
            // Toast.makeText(view.context, name, Toast.LENGTH_SHORT).show()
            Snackbar.make(view, name, Snackbar.LENGTH_LONG).show()
        }
        Log.d("FragmentTag", "HomeFragment Call ")
        // Inflate the layout for this fragment
        return  view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }
}