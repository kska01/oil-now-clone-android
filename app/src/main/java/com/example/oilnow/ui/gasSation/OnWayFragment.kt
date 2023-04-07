package com.example.oilnow.ui.gasSation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.oilnow.R
import com.example.oilnow.databinding.BottomSheetDialogFilterBinding
import com.example.oilnow.databinding.FragmentOnWayBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class OnWayFragment : Fragment() {

    private var _binding: FragmentOnWayBinding? = null
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
        _binding = FragmentOnWayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView: BottomNavigationView = binding.gasStationOnWayBottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.around -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_onWayFragment_to_gasStationFragment)
                    true
                }
                R.id.filter -> {
                    val dialog = BottomSheetDialog(requireContext())
                    val dialogBinding = BottomSheetDialogFilterBinding.inflate(layoutInflater)
                    dialog.setContentView(dialogBinding.root)
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_onWayFragment_to_gasStationFragment)
                    dialog.show()
                    true
                }
                R.id.local_currency -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_onWayFragment_to_localCurrencyFragment)
                    true
                }
                R.id.favorite -> {
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_onWayFragment_to_favoriteFragment)
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