package com.mashup.feature.danggn.data.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DanggnRankingSingleRoundCheckResponse(
    @Json(name = "danggnRankingReward")
    val danggnRankingReward: DanggnRankingReward,
    @Json(name = "dateCount")
    val dateCount: Int,
    @Json(name = "endDate")
    val endDate: String,
    @Json(name = "number")
    val number: Int,
    @Json(name = "startDate")
    val startDate: String
) {
    @JsonClass(generateAdapter = true)
    data class DanggnRankingReward(
        @Json(name = "comment")
        val comment: String?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "status")
        val status: String
    ) {
        enum class DanggnRankingRewardStatus {
            FIRST_PLACE_MEMBER_NOT_EXISTED,        // 1등한 사람이 없는 경우(1회차)
            FIRST_PLACE_MEMBER_NOT_REGISTERED,    // 회차는 진행되었지만, 1등한 사람이 리워드를 등록하지 않은 경우
            FIRST_PLACE_MEMBER_REGISTERED,
            UNKNOWN;      // 회차가 진행되었고 1등한 사람이 리워드를 등록한 경우

            companion object {
                fun getDanggnRankingRewardStatus(status: String) = values().find { it.name == status } ?: UNKNOWN
            }
        }
    }
}