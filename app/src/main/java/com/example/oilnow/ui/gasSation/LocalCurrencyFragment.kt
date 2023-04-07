package com.example.oilnow.ui.gasSation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oilnow.R
import com.example.oilnow.databinding.BottomSheetDialogFilterBinding
import com.example.oilnow.databinding.FragmentLocalCurrencyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class LocalCurrencyFragment : Fragment() {

    private var _binding: FragmentLocalCurrencyBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocalCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView: BottomNavigationView = binding.localCurrencyBottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.around -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_localCurrencyFragment_to_gasStationFragment)
                    true
                }
                R.id.filter -> {
                    val dialog = BottomSheetDialog(requireContext())
                    val dialogBinding = BottomSheetDialogFilterBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_localCurrencyFragment_to_gasStationFragment)
                    dialog.show()
                    true
                }
                R.id.on_way -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_localCurrencyFragment_to_onWayFragment)
                    true
                }
                R.id.favorite -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_localCurrencyFragment_to_favoriteFragment2)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}