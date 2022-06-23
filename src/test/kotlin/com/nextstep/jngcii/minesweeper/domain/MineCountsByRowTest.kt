package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineCountsByRowTest {
    @ParameterizedTest
    @MethodSource("rowCountAndExpected")
    fun `단순 MineCountsByRow 객체 생성 확인`(rowCount: Int, expected: List<Int>) {
        assertThat(MineCountsByRow(rowCount).toList()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun rowCountAndExpected() = listOf(
            Arguments.of(0, emptyList<Int>()),
            Arguments.of(1, listOf(0)),
            Arguments.of(5, listOf(0, 0, 0, 0, 0)),
        )
    }
}
