package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class LocationsTest {
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
    @MethodSource("rowCountAndColumnCountAndExpected")
    fun `Locations 객체 생성시 pairs 프로퍼티 확인`(
        meta: MineMapMeta,
        expected: List<Location>
    ) {
        val locations = Locations(meta)

        assertThat(locations.locations).isEqualTo(expected)
    }

    @Test
    fun `가장 앞부터 순서대로 조작하는 전략으로 지뢰 선택시 결과 확인`() {
        val locations = Locations(MineMapMeta(2, 3))

        val strategyPickOrderly = PickStrategy { target, count ->
            target.locations
                .take(count)
                .forEach { it.pick() }
        }

        locations.pickMines(3, strategyPickOrderly)

        val pairs = locations.locations

        assertThat(pairs.filter { it.isMine }).isEqualTo(
            listOf(Location(0, 0), Location(0, 1), Location(1, 0))
        )

        assertThat(pairs.filter { !it.isMine }).isEqualTo(
            listOf(Location(1, 1), Location(2, 0), Location(2, 1))
        )
    }

    @Test
    fun `지뢰 위치 확인`() {
        val locations = Locations(MineMapMeta(2, 3))

        locations.locations
            .filter { it.x == 1 }
            .forEach { it.pick() }

        assertAll(
            { assertThat(locations.check(0, 0)).isFalse },
            { assertThat(locations.check(0, 1)).isFalse },
            { assertThat(locations.check(1, 0)).isTrue },
            { assertThat(locations.check(1, 1)).isTrue },
            { assertThat(locations.check(2, 0)).isFalse },
            { assertThat(locations.check(2, 1)).isFalse },
        )
    }

    @ParameterizedTest
    @CsvSource(
        value = ["3:0", "0:2"],
        delimiter = ':'
    )
    fun `잘못된 지뢰 위치 확인시 예외 발생`(x: Int, y: Int) {
        val locations = Locations(MineMapMeta(2, 3))

        assertThrows<IllegalArgumentException>(
            "해당 좌표에 대한 Location이 존재하지 않습니다. (x:$x, y:$y)"
        ) { locations.check(x, y) }
    }

    companion object {
        @JvmStatic
        fun rowCountAndColumnCountAndExpected() = listOf(
            Arguments.of(
                MineMapMeta(2, 3),
                listOf(
                    Location(0, 0),
                    Location(0, 1),
                    Location(1, 0),
                    Location(1, 1),
                    Location(2, 0),
                    Location(2, 1),
                )
            ),
            Arguments.of(
                MineMapMeta(1, 2),
                listOf(
                    Location(0, 0),
                    Location(1, 0),
                )
            )
        )
    }
}
