package business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `특정 위치에 지뢰이면 ture`() {
        // given
        val board = Board(
            listOf(
                listOf(Cell(CellStatus.MINE), Cell(CellStatus.EMPTY)),
                listOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            )
        )

        // when
        val result = board.isMine(Point(0, 0))

        // then
        result shouldBe true
    }

    @Test
    fun `특정 위치에 지뢰가 아니면 false`() {
        // given
        val board = Board(
            listOf(
                listOf(Cell(CellStatus.MINE), Cell(CellStatus.EMPTY)),
                listOf(Cell(CellStatus.EMPTY), Cell(CellStatus.EMPTY)),
            )
        )

        // when
        val result = board.isMine(Point(0, 1))

        // then
        result shouldBe false
    }
}
