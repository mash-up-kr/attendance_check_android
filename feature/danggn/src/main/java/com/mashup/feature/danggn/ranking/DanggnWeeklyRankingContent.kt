package com.mashup.feature.danggn.ranking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.core.common.R
import com.mashup.core.ui.colors.Brand500
import com.mashup.core.ui.colors.Gray100
import com.mashup.core.ui.colors.Gray50
import com.mashup.core.ui.colors.Gray500
import com.mashup.core.ui.colors.Gray600
import com.mashup.core.ui.colors.Gray950
import com.mashup.core.ui.extenstions.noRippleClickable
import com.mashup.core.ui.theme.MashUpTheme
import com.mashup.core.ui.typography.Body2
import com.mashup.core.ui.typography.Caption2
import com.mashup.core.ui.typography.Header2
import com.mashup.core.ui.typography.SubTitle2

@Composable
fun DanggnWeeklyRankingContent(
    modifier: Modifier = Modifier,
    allRoundList: List<DanggnRankingViewModel.AllRound> = listOf(),
    onClickAnotherRounds: () -> Unit = {},  // 이걸 누를 때 랭킹 회차 팝업 보여줌
    onClickHelpButton: () -> Unit = {},
) {
    var index by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Gray50),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "2주의 당근 랭킹",
                    style = SubTitle2,
                    fontWeight = FontWeight.SemiBold,
                    color = Gray950,
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp)
                        .noRippleClickable {
                            onClickHelpButton()
                        },
                    painter = painterResource(id = R.drawable.ic_info_inverse),
                    contentDescription = null,
                    alignment = Alignment.CenterEnd
                )
            }
        }

        Spacer(modifier = Modifier.height(27.dp))

        Text(
            modifier = Modifier,
            text = "${allRoundList.getOrNull(index)?.number ?: "비밀"}회차",
            style = Header2,
            color = Brand500,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Gray100)
                .noRippleClickable {
                    onClickAnotherRounds()
                }
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // substring(2) 를 통해 2023-06-15 -> 23-06-15로 변환합니다
            val (startDate, endDate) = remember(
                allRoundList.getOrNull(index)?.startDate ?: "",
                allRoundList.getOrNull(index)?.endDate ?: ""
            ) {
                allRoundList.getOrNull(index)?.startDate?.substring(2)?.replace("-", ".") to
                        allRoundList.getOrNull(index)?.endDate?.substring(2)?.replace("-", ".")
            }

            Text(
                text = "$startDate - $endDate",
                style = Caption2,
                color = Gray500,
                fontWeight = FontWeight.Medium
            )
            Image(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(12.dp),
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = null,
                alignment = Alignment.BottomCenter
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        Text(
            color = Gray600,
            style = Body2,
            text = buildAnnotatedString {
                append("랭킹 종료까지 ")
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    // TODO 서버 값 넣기
                    append("3일 ")
                }
                append("남았어요")
            })

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
@Preview
fun WeeklyRankingPreview() {
    MashUpTheme {
        DanggnWeeklyRankingContent()
    }
}