package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.cellsOf
import minesweeper.closedEmptyCellOf
import minesweeper.openedEmptyCellOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class PlayableBoardTest {
    @Test
    fun `빈 보드를 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { PlayableBoard(cellsOf()) }
    }

    @Test
    fun `칸 주위의 지회 개수를 센다`() {
        val board =
            PlayableBoard(
                cellsOf(
                    closedEmptyCellOf(y = 0, x = 0),
                    closedEmptyCellOf(y = 0, x = 1),
                    closedEmptyCellOf(y = 0, x = 2),
                    closedEmptyCellOf(y = 1, x = 0),
                    openedEmptyCellOf(y = 1, x = 1),
                    undetonatedMineCellOf(y = 1, x = 2),
                    closedEmptyCellOf(y = 2, x = 0),
                    undetonatedMineCellOf(y = 2, x = 1),
                    closedEmptyCellOf(y = 2, x = 2),
                ),
            )
//        C C C
//        C 2 *
//        C * C

        val count = board.countMines(y = 1, x = 1)

        count shouldBe 2
    }

    @Test
    fun `지뢰가 있는 칸을 열면 지뢰가 폭파되고 지뢰가 폭파된 보드가 된다`() {
        var board: Board
        board = PlayableBoard(cellsOf(undetonatedMineCellOf(y = 0, x = 0)))

        board = board.open(0, 0)

        board.shouldBeTypeOf<MineDetonatedBoard>()
    }

    @Test
    fun `빈 칸을 열고 게임이 진행한다`() {
        var board: Board
        board =
            PlayableBoard(
                cellsOf(
                    closedEmptyCellOf(y = 0, x = 0),
                    undetonatedMineCellOf(y = 0, x = 1),
                    closedEmptyCellOf(y = 1, x = 0),
                    closedEmptyCellOf(y = 1, x = 1),
                ),
            )
//        C *
//        C C

        board = board.open(0, 0)
//        1 *
//        C C

        board.shouldBeTypeOf<PlayableBoard>()
    }

    @Test
    fun `모든 빈 칸이 열리면 플레이어가 승리한 보드가 된다`() {
        var board: Board
        board =
            PlayableBoard(
                cellsOf(
                    closedEmptyCellOf(y = 0, x = 0),
                    openedEmptyCellOf(y = 0, x = 1),
                    undetonatedMineCellOf(y = 1, x = 0),
                    undetonatedMineCellOf(y = 1, x = 1),
                ),
            )
//        C 2
//        * *

        board = board.open(0, 0)

        board.shouldBeTypeOf<PlayerWonBoard>()
    }
}
