package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.openedCellOf
import minesweeper.unOpenedCellOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class BoardsTest {
    @Test
    fun `빈 판을 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { PlayableBoard(emptyMap()) }
    }

    @Test
    fun `PlayerWonBoard 에는 지뢰가 없는 모든 칸이 열려있어야 한다`() {
        val cells =
            mapOf(
                undetonatedMineCellOf(y = 0, x = 0),
                openedCellOf(y = 0, x = 1),
                unOpenedCellOf(y = 1, x = 0),
                openedCellOf(y = 1, x = 1),
            )
//        * 1
//        C 1
        assertThrows<IllegalArgumentException> { PlayerWonBoard(cells) }
    }

    @Test
    fun `MineDetonatedBoard 에는 폭파한 지뢰가 1 이상 있다`() {
        val cells =
            mapOf(
                undetonatedMineCellOf(y = 0, x = 0),
                openedCellOf(y = 0, x = 1),
                unOpenedCellOf(y = 1, x = 0),
                openedCellOf(y = 1, x = 1),
            )
//        * 1
//        C 1
        assertThrows<IllegalArgumentException> { MineDetonatedBoard(cells) }
    }

    @Test
    fun `각 코너 주위의 지뢰 개수를 센다`() {
        val cells =
            mapOf(
                openedCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                openedCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                unOpenedCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                openedCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                openedCellOf(y = 2, x = 2),
            )
        val board = PlayableBoard(cells)
//        2 * 2
//        * C *
//        2 * 2

        board.countMines(y = 0, x = 0) shouldBe 2
        board.countMines(y = 2, x = 0) shouldBe 2
        board.countMines(y = 2, x = 2) shouldBe 2
        board.countMines(y = 0, x = 2) shouldBe 2
    }

    @Test
    fun `가운데 칸 주위의 지회 개수를 센다`() {
        val cells =
            mapOf(
                unOpenedCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                unOpenedCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                openedCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                unOpenedCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                unOpenedCellOf(y = 2, x = 2),
            )
        val board = PlayableBoard(cells)
//        C * C
//        * 4 *
//        C * C

        val count = board.countMines(y = 1, x = 1)

        count shouldBe 4
    }
}
