package com.mashup.ui.danggn

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.mashup.R
import com.mashup.base.BaseActivity
import com.mashup.core.ui.theme.MashUpTheme
import com.mashup.databinding.ActivityShakeDanggnBinding
import com.mashup.feature.danggn.ShakeDanggnScreen

class ShakeDanggnActivity : BaseActivity<ActivityShakeDanggnBinding>() {
    override val layoutId: Int = R.layout.activity_shake_danggn

    override fun initViews() {
        super.initViews()

        viewBinding.shakeDanggnScreen.setContent {
            MashUpTheme {
                ShakeDanggnScreen(
                    modifier = Modifier.fillMaxSize(),
                    onClickBackButton = { onBackPressed() }
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ShakeDanggnActivity::class.java).apply {

        }
    }
}