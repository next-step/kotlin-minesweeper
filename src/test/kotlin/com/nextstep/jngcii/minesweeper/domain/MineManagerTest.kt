package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineManagerTest {
    @ParameterizedTest
    @DisplayName(
        """
        x x x (row 0)
        x x x (row 1)
        x x x (row 2)
        
        위 배열에 지뢰
    """
    )
    @MethodSource("mineCountAndExpected")
    fun `무조건 지뢰를 배치하라는 전략을 통해 3개의 row에 지뢰를 배치하는 결과 확인`(
        mineCount: Int,
        expected: List<Int>
    ) {
        val rowCount = 3
        val justTrueStrategy = BooleanStrategy { true }
        val manager = MineManager(justTrueStrategy)

        val targetMineCountsByRow = MineCountsByRow(rowCount)
        manager.assignMinesOnRow(targetMineCountsByRow, 0, mineCount)

        assertThat(targetMineCountsByRow.toList()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun mineCountAndExpected() = listOf(
            Arguments.of(0, listOf(0, 0, 0)),
            Arguments.of(1, listOf(1, 0, 0)),
            Arguments.of(5, listOf(2, 2, 1)),
            Arguments.of(10, listOf(4, 3, 3)),
        )
    }
}
