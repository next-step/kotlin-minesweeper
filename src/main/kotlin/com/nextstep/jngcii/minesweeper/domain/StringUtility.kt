package com.nextstep.jngcii.minesweeper.domain

object StringUtility {
    private const val ONLY_POSITIVE_INT_REQUIRE_MESSAGE = "양의 정수만 입력 가능합니다."

    fun convertToPositiveInt(value: String): Int {
        val intValue = kotlin.runCatching {
            value.toInt()
        }.getOrElse {
            throw IllegalArgumentException(ONLY_POSITIVE_INT_REQUIRE_MESSAGE)
        }

        if (intValue <= 0) {
            throw IllegalArgumentException(ONLY_POSITIVE_INT_REQUIRE_MESSAGE)
        }

        return intValue
    }
}
