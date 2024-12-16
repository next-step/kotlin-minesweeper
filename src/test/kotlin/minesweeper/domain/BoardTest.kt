package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.boardOf
import minesweeper.emptyCellOf
import minesweeper.minedCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class BoardTest {
    @Test
    fun `빈 판을 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { Board(emptyMap()) }
    }

    @Test
    fun `각 코너 주위의 지뢰 개수를 센다`() {
        val board =
            boardOf(
                emptyCellOf(y = 0, x = 0),
                minedCellOf(y = 0, x = 1),
                emptyCellOf(y = 0, x = 2),
                minedCellOf(y = 1, x = 0),
                emptyCellOf(y = 1, x = 1),
                minedCellOf(y = 1, x = 2),
                emptyCellOf(y = 2, x = 0),
                minedCellOf(y = 2, x = 1),
                emptyCellOf(y = 2, x = 2),
            )

        board.countMines(y = 0, x = 0) shouldBe 2
        board.countMines(y = 2, x = 0) shouldBe 2
        board.countMines(y = 2, x = 2) shouldBe 2
        board.countMines(y = 0, x = 2) shouldBe 2
    }

    @Test
    fun `가운데 칸 주위의 지회 개수를 센다`() {
        val board =
            boardOf(
                emptyCellOf(y = 0, x = 0),
                minedCellOf(y = 0, x = 1),
                emptyCellOf(y = 0, x = 2),
                minedCellOf(y = 1, x = 0),
                emptyCellOf(y = 1, x = 1),
                minedCellOf(y = 1, x = 2),
                emptyCellOf(y = 2, x = 0),
                minedCellOf(y = 2, x = 1),
                emptyCellOf(y = 2, x = 2),
            )

        val count = board.countMines(y = 1, x = 1)

        count shouldBe 4
    }
}
