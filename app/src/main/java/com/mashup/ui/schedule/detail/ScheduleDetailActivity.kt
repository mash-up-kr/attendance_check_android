package com.mashup.ui.schedule.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mashup.R
import com.mashup.base.BaseActivity
import com.mashup.constant.EXTRA_SCHEDULE_ID
import com.mashup.core.common.constant.SCHEDULE_NOT_FOUND
import com.mashup.core.ui.theme.MashUpTheme
import com.mashup.databinding.ActivityScheduleDetailBinding
import com.mashup.ui.attendance.platform.PlatformAttendanceActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ScheduleDetailActivity : BaseActivity<ActivityScheduleDetailBinding>() {
    override val layoutId = R.layout.activity_schedule_detail

    private val viewModel: ScheduleDetailViewModel by viewModels()

    override fun initViews() {
        super.initViews()

        viewBinding.composeView.setContent {
            val state by viewModel.scheduleState.collectAsState()

            MashUpTheme {
                ScheduleDetailScreen(
                    state = state,
                    copyToClipboard = ::copyToClipboard,
                    moveToPlatformAttendance = ::moveToPlatformAttendance,
                    onBackPressed = { finish() },
                )
            }
        }
    }

    override fun initObserves() {
        super.initObserves()
        flowLifecycleScope {
            viewModel.scheduleState.collectLatest { state ->
                when (state) {
                    ScheduleState.Loading -> {
                        showLoading()
                    }

                    is ScheduleState.Success -> {
                        hideLoading()
                    }

                    is ScheduleState.Error -> {
                        hideLoading()
                        handleCommonError(state.code)
                        handleScheduleDetailErrorCode(state)
                    }

                    else -> {
                        hideLoading()
                    }
                }
            }
        }
    }

    private fun handleScheduleDetailErrorCode(error: ScheduleState.Error) {
        val codeMessage = when (error.code) {
            SCHEDULE_NOT_FOUND -> {
                "스케줄 정보를 찾을 수 없습니다. 다시 시도해주세요"
            }
            else -> {
                null
            }
        }
        codeMessage?.run { showToast(this) }
    }

    private fun copyToClipboard(text: String) {
        (getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager)?.let { clipboardManager ->
            val clip = ClipData.newPlainText("location", text)
            clipboardManager.setPrimaryClip(clip)
        }
    }

    private fun moveToPlatformAttendance() {
        val intent = PlatformAttendanceActivity.newIntent(this, viewModel.scheduleId)
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context, scheduleId: Int) =
            Intent(context, ScheduleDetailActivity::class.java).apply {
                putExtra(EXTRA_SCHEDULE_ID, scheduleId)
            }
    }
}
