package com.example.moremenu

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.moremenu.model.MenuType
import com.example.notice.NoticeActivity
import com.mashup.core.ui.theme.MashUpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreMenuActivity : ComponentActivity() {

    private val moreMenuViewModel: MoreMenuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MashUpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MoreMenuRoute(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        moreMenuViewModel = moreMenuViewModel,
                        onNavigateBackStack = ::finish,
                        onNavigateMenu = { menu ->
                            if (menu.type == MenuType.NOTI) {
                                val intent = Intent(this, NoticeActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    )
                }
            }
        }
    }
}
