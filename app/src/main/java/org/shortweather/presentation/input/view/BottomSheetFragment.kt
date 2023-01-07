package org.shortweather.presentation.input.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import org.shortweather.R
import org.shortweather.databinding.BottomSheetContentBinding
import org.shortweather.presentation.input.adapter.BottomSheetAdapter
import org.shortweather.presentation.input.adapter.BottomSheetItem
import org.shortweather.presentation.input.viewmodel.InputInfoViewModel

@AndroidEntryPoint
class BottomSheetFragment(val target: String) : BottomSheetDialogFragment() {
    private var _binding: BottomSheetContentBinding? = null
    private val binding: BottomSheetContentBinding
        get() = requireNotNull(_binding) { "${this::class.java.simpleName} error." }
    private lateinit var list: MutableList<BottomSheetItem>
    private val viewModel by activityViewModels<InputInfoViewModel>() // 이제 이 뷰모델은 activity의 뷰모델 객체를 공유하는 개념, 별개의 객체가 아니다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        inputCancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewKindCheck()

        val adapter = BottomSheetAdapter(
            list,
            object : BottomSheetAdapter.OnItemClickListener { // 어댑터의 인터페이스 구현
                override fun onItemClick(item: BottomSheetItem) = when (target) {
                    "gender" -> {
                        viewModel.inputGender.value = item.contents
                    }
                    "age" -> {
                        viewModel.inputAge.value = item.contents
                    }
                    else -> {
                        viewModel.inputSense.value = item.contents
                    }
                }
            })
        binding.rvContents.adapter = adapter // 리사이클러뷰에 어댑터 연결
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewKindCheck() { // 바텀시트의 종류가 성별/연령/민감도인지 확인하고 이에 대응하여 아이템들을 생성함,
        if (target == "gender") {
            binding.tvBottomSheetHeader.text = getString(R.string.input_gender)
            list = mutableListOf(
                BottomSheetItem("여자"),
                BottomSheetItem("남자"),
            )
        } else if (target == "age") {
            binding.tvBottomSheetHeader.text = getString(R.string.input_age)
            list = mutableListOf(
                BottomSheetItem("10대"),
                BottomSheetItem("20대"),
                BottomSheetItem("30대"),
                BottomSheetItem("40대"),
                BottomSheetItem("50대"),
                BottomSheetItem("60대 이상"),
            )
        } else {
            binding.tvBottomSheetHeader.text = getString(R.string.input_sensitivity)
            list = mutableListOf(
                BottomSheetItem("추위를 잘 타요"),
                BottomSheetItem("보통이에요"),
                BottomSheetItem("더위를 잘 타요"),
                BottomSheetItem("추위, 더위 모두 타요"),
            )
        }
    }

    private fun inputCancel() { // 입력 실패 상황 (바텀시트가 등장한 상태에서 아무것도 선택하지 않고 바텀시트 이탈 시)
        if (target == "gender") {
            viewModel.inputGender.value = "" // 아무 값도 선택하지 않은 상태이므로 빈칸 처리 -> 뷰모델에서 false 처리됨
        } else if (target == "age") {
            viewModel.inputAge.value = ""
        } else {
            viewModel.inputSense.value = ""
        }
    }

    companion object {
        const val TAG = "BottomSheet"
    }

}