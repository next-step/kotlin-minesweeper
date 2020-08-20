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
    fun `지뢰 위치에 지뢰 심볼("*")을 놓는다`() {
        // given
        val mines = Mines(listOf(Position(0, 0), Position(1, 1)).map { Mine(it) })
        val map = MineMap(MapDimension(2, 2), mines)

        // when
        val mineMap = mines.setIntoMap(map.getMineCountedMap())

        // then
        assertThat(mineMap[0][0]).isEqualTo("*")
        assertThat(mineMap[1][1]).isEqualTo("*")
    }

    @Test
    fun `지뢰가 없는 곳은 지뢰 개수를 표시한다`() {
        // given
        val mines = Mines(listOf(Position(0, 0), Position(1, 1)).map { Mine(it) })
        val map = MineMap(MapDimension(2, 2), mines)

        // when
        val mineMap = mines.setIntoMap(map.getMineCountedMap())

        // then
        assertThat(mineMap[1][0]).isEqualTo("2")
        assertThat(mineMap[0][1]).isEqualTo("2")
    }
}
