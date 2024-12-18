package minesweeper.domain

import minesweeper.closedEmptyCellOf
import minesweeper.openedEmptyCellOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class CompletedBoardTest {
    @Test
    fun `플레이어가 승리한 보드에는 지뢰가 없는 모든 칸이 열려있어야 한다`() {
        val cells =
            mapOf(
                undetonatedMineCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
            )
//        * 1
//        C 1
        assertThrows<IllegalArgumentException> { PlayerWonBoard(cells) }
    }

    @Test
    fun `지뢰가 폭발한 보드에는 폭파된 지뢰가 1 이상 있다`() {
        val cells =
            mapOf(
                undetonatedMineCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
            )
//        * 1
//        C 1
        assertThrows<IllegalArgumentException> { MineDetonatedBoard(cells) }
    }
}
