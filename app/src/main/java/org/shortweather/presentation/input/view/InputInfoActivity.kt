package org.shortweather.presentation.input.view

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputInfoBinding
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputInfoActivity: BindingActivity<ActivityInputInfoBinding>(R.layout.activity_input_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_info)
    }
}