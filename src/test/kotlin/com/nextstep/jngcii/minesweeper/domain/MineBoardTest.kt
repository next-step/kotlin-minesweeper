package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineBoardTest {
    @DisplayName(
        """
        ## 예시 ##
        
        rowCount    (=가로 수) : 2
        columnCount (=세로 수) : 3
        
        좌표
        x(0,0) x(1,0) x(2,0)
        x(0,1) x(1,1) x(2,1)
    """
    )
    @ParameterizedTest
    @MethodSource("rowCountAndColumnCountAndLocationsAndByRows")
    fun `Locations 객체 생성시 pairs 프로퍼티 확인`(
        meta: MineBoardMeta,
        locations: List<Location>,
        locationsByRow: List<List<Location>>
    ) {
        val mineBoard = MineBoard(meta)

        assertAll(
            { assertThat(mineBoard.locations).isEqualTo(locations) },
            { assertThat(mineBoard.locationsByRow).isEqualTo(locationsByRow) }
        )
    }

    @Test
    fun `가장 앞부터 순서대로 조작하는 전략으로 지뢰 선택시 결과 확인`() {
        val mineBoard = MineBoard(MineBoardMeta(2, 3))

        val notingOrderStrategy = OrderStrategy { total, count ->
            List(total) { it }.take(count)
        }

        mineBoard.pickMines(3, notingOrderStrategy)
        mineBoard.recordRisk()

        val locations = mineBoard.locations

        assertAll(
            {
                assertThat(locations.filter { it.isMine }).isEqualTo(
                    listOf(Location(0, 0), Location(0, 1), Location(1, 0))
                )
            },
            {
                assertThat(locations.filter { !it.isMine }).isEqualTo(
                    listOf(Location(1, 1), Location(2, 0), Location(2, 1))
                )
            }
        )
    }

    @Test
    fun `가장 앞부터 순서대로 조작하는 전략으로 지뢰 선택시 ri나 기록 결과 확인`() {
        val mineBoard = MineBoard(MineBoardMeta(2, 3))

        val notingOrderStrategy = OrderStrategy { total, count ->
            List(total) { it }.take(count)
        }

        mineBoard.pickMines(3, notingOrderStrategy)
        mineBoard.recordRisk()

        val locations = mineBoard.locations

        assertThat(locations.map { it.risk }).isEqualTo(
            listOf(0, 0, 0, 3, 1, 1)
        )
    }

    companion object {
        @JvmStatic
        fun rowCountAndColumnCountAndLocationsAndByRows() = listOf(
            Arguments.of(
                MineBoardMeta(2, 3),
                listOf(
                    Location(0, 0),
                    Location(0, 1),
                    Location(1, 0),
                    Location(1, 1),
                    Location(2, 0),
                    Location(2, 1),
                ),
                listOf(
                    listOf(Location(0, 0), Location(1, 0), Location(2, 0)),
                    listOf(Location(0, 1), Location(1, 1), Location(2, 1))
                )
            ),
            Arguments.of(
                MineBoardMeta(1, 2),
                listOf(
                    Location(0, 0),
                    Location(1, 0),
                ),
                listOf(
                    listOf(Location(0, 0), Location(1, 0))
                )
            )
        )
    }
}
