package org.shortweather.presentation.input.bottomsheet

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.BottomSheetGenderContentBinding
import org.shortweather.presentation.input.BottomSheetAdapter
import org.shortweather.presentation.input.BottomSheetItem
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel

@AndroidEntryPoint
class BottomSheetGender(val target: String, val adapter: BottomSheetAdapter) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetGenderContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetGenderContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(target == "gender"){
            binding.tvBottomSheetGenderHeader.setText(R.string.input_gender)
        } else if(target == "age"){
            binding.tvBottomSheetGenderHeader.setText(R.string.input_age)
        } else{
            binding.tvBottomSheetGenderHeader.setText(R.string.input_sensitivity)
        }
        binding.rvContents.adapter = adapter
    }

    companion object {
        const val TAG = "BottomSheetGender"
    }

}
