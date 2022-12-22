package domain

import domain.block.Land
import domain.block.Mine
import domain.coord.Coordinate
import domain.coord.RelativeCoordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CoordinateTest {

    @Test
    fun `블록을 만드려는 곳에 지뢰가 존재하면 Mine이 반환횐다`() {
        // given
        val coordinate = Coordinate(1, 2)
        val mines = LocationOfMines(listOf(Coordinate(1, 2)))
        // when
        val block = coordinate.toBlock(mines)

        // then
        assertThat(block).isInstanceOf(Mine::class.java)
    }

    @Test
    fun `블록을 만드려는 곳에 지뢰가 없으면 Land 가 반환횐다`() {
        // given
        val coordinate = Coordinate(1, 2)
        val mines = LocationOfMines(listOf(Coordinate(0, 0)))
        // when
        val block = coordinate.toBlock(mines)

        // then
        assertThat(block).isInstanceOf(Land::class.java)
    }

    @ParameterizedTest
    @CsvSource(value = ["0, 0, -1, 1", "0, 0, 0, -1"])
    internal fun `y 또는 x 좌표의 합중 하나라도 음수이면 두 좌표는 더할 수 없다`(y1: Int, x1: Int, y2: Int, x2: Int) {
        // given
        val coordinate1 = Coordinate(y1, x1)
        val coordinate2 = RelativeCoordinate(y2, x2)

        // when
        val isPossiblePlus = coordinate1.isPossiblePlus(coordinate2)

        // then
        assertThat(isPossiblePlus).isFalse
    }

    @Test
    internal fun `y 와 x 좌표의 각 합 모두 양수이면 두 좌표는 더할 수 있다`() {
        // given
        val coordinate1 = Coordinate(0, 0)
        val coordinate2 = Coordinate(1, 1)

        // when
        val isPossiblePlus = coordinate1.isPossiblePlus(coordinate2)

        // then
        assertThat(isPossiblePlus).isTrue
    }
}
