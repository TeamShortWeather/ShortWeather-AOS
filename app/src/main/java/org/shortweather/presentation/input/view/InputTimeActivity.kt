package org.shortweather.presentation.input.view

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivityInputTimeBinding
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class InputTimeActivity: BindingActivity<ActivityInputTimeBinding>(R.layout.activity_input_time) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_time)
    }
}