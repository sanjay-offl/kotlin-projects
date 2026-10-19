package com.hfad.bitsandpizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hfad.bitsandpizzas.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        (requireActivity() as androidx.appcompat.app.AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.choose_pizza_type),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var text = when (pizzaType) {
                    R.id.radio_diavolo -> getString(R.string.diavolo_pizza)
                    else -> getString(R.string.funghi_pizza)
                }
                text += if (binding.parmesan.isChecked) {
                    getString(R.string.extra_parmesan)
                } else {
                    ""
                }
                text += if (binding.chiliOil.isChecked) {
                    getString(R.string.extra_chili_oil)
                } else {
                    ""
                }
                Snackbar.make(binding.fab, text, Snackbar.LENGTH_LONG).show()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}