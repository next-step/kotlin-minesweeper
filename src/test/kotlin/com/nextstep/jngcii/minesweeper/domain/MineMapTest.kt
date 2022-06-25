package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class MineMapTest {
    @DisplayName(
        """
        - AS-IS (Locations 객체)
        x(0,0,t) x(1,0,f) x(2,0,f)
        x(0,1,f) x(1,1,t) x(2,1,f)
        
        - TO-BE (MineMap)
        true  false false
        false true  false
    """
    )
    @ParameterizedTest
    @MethodSource("locationsAndRows")
    fun `MineMap 생성시 locations - rows 변환 테스트`(
        locationList: List<Location>,
        rows: List<Row>
    ) {
        val mineMap = MineMap.build(
            locations = locationList.toLocations()
        )

        assertThat(mineMap.rows).isEqualTo(rows)
    }

    private fun List<Location>.toLocations(): Locations {
        val meta = MineMapMeta(
            rowCount = this.filter { it.x == 0 }.size,
            columnCount = this.filter { it.y == 0 }.size
        )
        val locations = Locations(meta)

        this.forEach { location ->
            if (location.isMine) {
                locations.pairs.find { it.x == location.x && it.y == location.y }?.pick()
            }
        }

        return locations
    }

    companion object {
        @JvmStatic
        fun locationsAndRows() = listOf(
            Arguments.of(
                listOf(
                    Location(0, 0).apply { pick() },
                    Location(0, 1),
                    Location(1, 0),
                    Location(1, 1).apply { pick() },
                    Location(2, 0),
                    Location(2, 1),
                ),
                listOf(
                    Row(listOf(true, false, false)),
                    Row(listOf(false, true, false))
                )
            ),
            Arguments.of(
                listOf(
                    Location(0, 0).apply { pick() },
                    Location(0, 1),
                    Location(0, 2),
                    Location(1, 0),
                    Location(1, 1).apply { pick() },
                    Location(1, 2).apply { pick() },
                ),
                listOf(
                    Row(listOf(true, false)),
                    Row(listOf(false, true)),
                    Row(listOf(false, true))
                )
            ),
        )
    }
}
