package minesweeper

import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import org.junit.jupiter.api.Test

class CellTest {
    @Test
    fun `좌표값을 비교 할 수 있다`() {
        val coordinate1 = Coordinate(axisX = 10, axisY = 15)
        val coordinate2 = Coordinate(axisX = 10, axisY = 15)
        coordinate1 shouldBeEqual coordinate2
    }

    @Test
    fun `좌표값 중 하나를 지뢰를 설치 할 수 있다`() {
        val coordinate = Coordinate(axisX = 10, axisY = 15)
        val cell = Cell(coordinate, true)

        cell.hasMine shouldBe true
        cell.coordinate shouldBe coordinate
    }

    @Test
    fun `해당 특정 좌표에 지뢰를 만들면 지뢰인지 확인해볼수 있다`() {
        val coordinate = Coordinate(axisX = 1, axisY = 1)
        val cell = Cell(coordinate, true)
        cell.hasMine shouldBe true
    }
}
