package com.example.GenesysAndroidChallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.genesysanchallenge.databinding.FragmentDetailsBinding
import com.example.genesysanchallenge.viewmodel.UsersViewModel

class UsersDetailsFragment : DialogFragment() {

    private lateinit var binding : FragmentDetailsBinding
    private lateinit var viewModel : UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL,
            android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UsersViewModel::class.java)
        val currentSelection = viewModel.usersCurrentSelection.value
        binding.cell.text = currentSelection.cell
        binding.phone.text = currentSelection.phone
        binding.email.text = currentSelection.email
        binding.username.text = currentSelection.login?.username
        Glide.with(requireContext()).load(currentSelection.image?.large).into(binding.large)
        binding.close.setOnClickListener {
            dismiss()
        }


    }


    companion object {

        @JvmStatic
        fun newInstance() =
            UsersDetailsFragment()
    }
}