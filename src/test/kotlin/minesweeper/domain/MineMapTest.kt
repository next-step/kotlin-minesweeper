package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapTest {

    @Test
    fun `지뢰 개수를 검증한다`() {
        // when
        val mineMap = MineMap(5, 5, mineCount = 10)

        // then
        assertThat(mineMap.mines.size()).isEqualTo(10)
    }

    @Test
    fun `지도 안 지뢰 위치에 지뢰 심볼("*")을 놓는다`() {
        // given
        val mines = Mines(listOf(MinePosition(0, 0), MinePosition(1, 1)).map { Mine(it) })
        val map = MineMap(dimension = MapDimension(2, 2), _mines = mines)

        // when
        val mineMap = mines.setIntoMap(map.stateOfMap())

        // then
        assertThat(mineMap[0][0]).isEqualTo("*")
        assertThat(mineMap[1][1]).isEqualTo("*")

        assertThat(mineMap[1][0]).isNotEqualTo("*")
        assertThat(mineMap[0][1]).isNotEqualTo("*")
    }
}
