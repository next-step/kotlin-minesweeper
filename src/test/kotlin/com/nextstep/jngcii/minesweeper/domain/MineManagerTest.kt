package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineManagerTest {
    private val justTrueStrategy = BooleanStrategy { true }
    private val firstOrderlyPickStrategy = PickStrategy { target, count -> target.take(count) }
    private val manager = MineManager(justTrueStrategy, firstOrderlyPickStrategy)

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
        val columnCount = 4
        val targetMineCountsByRow = MineCountsByRow(rowCount)
        manager.assignMinesOnRow(targetMineCountsByRow, 0, mineCount, columnCount)

        assertThat(targetMineCountsByRow.toList()).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("columnCountAndMineCountAndExpected")
    fun `처음부터 순서대로 픽하는 전략으로 줄의 지뢰 위치를 반환받는 것 확인`(
        columnCount: Int,
        mineCount: Int,
        expected: List<Int>
    ) {
        val actual = manager.selectMineLocation(columnCount, mineCount)

        assertThat(actual.indexes).isEqualTo(expected)
    }

    @Test
    fun `RandomPickStrategy 를 사용했을 때 mineCount가 더 많을 경우 예외 발생 확인`() {
        val randomPickStrategyManager = MineManager(
            booleanStrategy = justTrueStrategy,
            pickStrategy = RandomPickStrategy
        )

        val columnCount = 10
        val mineCount = 11

        assertThrows<IllegalStateException>("${columnCount}개 중 ${mineCount}개를 고를 수 없습니다.") {
            randomPickStrategyManager.selectMineLocation(columnCount, mineCount)
        }
    }

    companion object {
        @JvmStatic
        fun mineCountAndExpected() = listOf(
            Arguments.of(0, listOf(0, 0, 0)),
            Arguments.of(1, listOf(1, 0, 0)),
            Arguments.of(5, listOf(2, 2, 1)),
            Arguments.of(10, listOf(4, 3, 3)),
        )

        @JvmStatic
        fun columnCountAndMineCountAndExpected() = listOf(
            Arguments.of(5, 0, emptyList<Int>()),
            Arguments.of(5, 2, listOf(0, 1)),
            Arguments.of(5, 5, listOf(0, 1, 2, 3, 4)),
        )
    }
}
