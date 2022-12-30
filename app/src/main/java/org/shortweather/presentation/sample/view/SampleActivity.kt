package org.shortweather.presentation.sample.view

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.ActivitySampleBinding
import org.shortweather.presentation.sample.viewmodel.SampleViewModel
import org.shortweather.util.binding.BindingActivity

@AndroidEntryPoint
class SampleActivity : BindingActivity<ActivitySampleBinding>(R.layout.activity_sample) {
    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        setOnClickListener()
        setObservers()
        setAdapter() // setAdapters()
        setNavigation()
        startTargetActivity() // startTargetFragment(), Target -> ex) Sample
    }

    private fun initView() {
        // 뷰를 초기화 하는 로직을 작성합니다.
    }

    private fun setOnClickListener() {
        // onClickListener 등록 로직을 작성합니다.
        binding.btnSample.setOnClickListener {
            viewModel.getSample()
        }
    }

    private fun setObservers() {
        // viewModel의 LiveData를 관찰하는 Observer 등록 로직을 작성합니다.
        viewModel.sample.observe(this) { sample ->
            if (sample.data == "test") {
                binding.tvSample.text = "sample LiveData가 변경 되었습니다."
            }
        }
    }

    private fun setAdapter() {
        // adapter를 연결 하는 로직을 작성합니다.
        // 추가로 LiveData의 변경 사항을 adapter에 적용해야 하는 경우 ex) Recycler View
        // 해당 LiveData를 관찰 하는 Observer 등록 로직을 작성합니다.
    }

    private fun setNavigation() {
        // UI 컨트롤러 전환 등록 로직을 작성합니다. ex) ToolBar navigation icon 등록 절차
    }

    private fun startTargetActivity() {
        // Target UI 컨트롤러로 전환하는 로직을 작성합니다. ex) SignUpActivity -> SignInActivity
    }
}