package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import org.junit.jupiter.api.Test

class BoardTest {
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

    @Test
    fun `보드는 넓이 x 높이 만큼의 크기로 생성된다`() {
        val board = Board(10, 10)
        board.coordinates.size shouldBe 100
    }

    @Test
    fun `보드의 넓이와 높이느 30보다 클 수 없다`() {
        shouldThrow<IllegalArgumentException> {
            Board(31, 35)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }

    @Test
    fun `지뢰 없이 셀을 만들면 지뢰의 숫자는 0이다`() {
        val board = Board(10, 10)
        board.makeCell()
        board.countMines() shouldBe 0
    }

    @Test
    fun `지뢰를 포함한 셀을 만들면 지뢰의 숫자를 셀수 있다`() {
        val board = Board(10, 10)
        val mineCount = 10
        board.makeCell(mineCount)
        board.countMines() shouldBe 10
    }

    @Test
    fun `보드 전체 의 셀 보다 많은 지뢰를 만들 수 없다`() {
        val board = Board(5, 5)
        val mineCount = 50
        shouldThrow<IllegalArgumentException> {
            board.makeCell(mineCount)
        }.also {
            it.message shouldBe "지뢰의 개수가 전체 셀 보다 많을 수 없습니다"
        }
    }

    @Test
    fun `보드 크기 안의 셀을 찾을 수 있다`() {
        val board = Board(10, 10)
        board.makeCell(0)
        val cell = board.findCell(5, 5)
        cell.coordinate shouldBe Coordinate(axisX = 5, axisY = 5)
    }

    @Test
    fun `보드에 없는 좌표를 찾으려고 하면 에러가 발생한다`() {
        val board = Board(10, 10)
        board.makeCell(0)
        shouldThrow<IllegalArgumentException> {
            board.findCell(15, 15)
        }.also {
            it.message shouldBe "15, 15 좌표는 없습니다"
        }
    }
}
