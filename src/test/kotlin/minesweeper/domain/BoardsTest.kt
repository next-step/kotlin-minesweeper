package minesweeper.domain

import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.closedEmptyCellOf
import minesweeper.openedEmptyCellOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class BoardsTest {
    @Test
    fun `빈 보드를 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { PlayableBoard(emptyMap()) }
    }

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

    @Test
    fun `코너 주위의 지뢰 개수를 센다`() {
        val cells =
            mapOf(
                openedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                openedEmptyCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                openedEmptyCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                openedEmptyCellOf(y = 2, x = 2),
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
                closedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 0, x = 2),
                undetonatedMineCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
                undetonatedMineCellOf(y = 1, x = 2),
                closedEmptyCellOf(y = 2, x = 0),
                undetonatedMineCellOf(y = 2, x = 1),
                closedEmptyCellOf(y = 2, x = 2),
            )
        val board = PlayableBoard(cells)
//        C * C
//        * 4 *
//        C * C

        val count = board.countMines(y = 1, x = 1)

        count shouldBe 4
    }

    @Test
    fun `열려고 의도하는 좌표에 칸이 없으면 예외를 던진다`() {
        val cells = mapOf(closedEmptyCellOf(y = 0, x = 0))
        val board = PlayableBoard(cells)
        assertThrows<IllegalArgumentException> { board.open(99, 99) }
    }

    @Test
    fun `이미 열린 칸을 열 수 없다`() {
        val cells = mapOf(openedEmptyCellOf(y = 0, x = 0))
        val board = PlayableBoard(cells)
        assertThrows<IllegalStateException> { board.open(0, 0) }
    }

    @Test
    fun `지뢰가 있는 칸을 열면 지뢰가 폭파되고 지뢰가 폭파된 보드가 된다`() {
        val cells = mapOf(undetonatedMineCellOf(y = 0, x = 0))
        var board: Board
        board = PlayableBoard(cells)

        board = board.open(0, 0)

        board.shouldBeTypeOf<MineDetonatedBoard>()
    }

    @Test
    fun `칸에 주변에 지뢰가 있는 경우 해당 칸만 열린다`() {
        val cells =
            mapOf(
                closedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        C C
//        * *
        var board: Board
        board = PlayableBoard(cells)

        board = board.open(0, 0) as PlayableBoard

        board.cells shouldContainExactly
            mapOf(
                openedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        2 C
//        * *
    }

    @Test
    fun `지뢰와 인접하지 않은 주변의 모든 칸이 열린다`() {
        val cells =
            mapOf(
                closedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 0, x = 2),
                closedEmptyCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
                closedEmptyCellOf(y = 1, x = 2),
                closedEmptyCellOf(y = 2, x = 0),
                closedEmptyCellOf(y = 2, x = 1),
                undetonatedMineCellOf(y = 2, x = 2),
            )
        var board: Board
        board = PlayableBoard(cells)
//        C C *
//        C C C
//        C C *

        board = board.open(0, 0)

        board.cells shouldContainExactly
            mapOf(
                openedEmptyCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 0, x = 2),
                openedEmptyCellOf(y = 1, x = 0),
                openedEmptyCellOf(y = 1, x = 1),
                closedEmptyCellOf(y = 1, x = 2),
                openedEmptyCellOf(y = 2, x = 0),
                openedEmptyCellOf(y = 2, x = 1),
                undetonatedMineCellOf(y = 2, x = 2),
            )
//        0 1 *
//        1 1 C
//        1 1 *
    }

    @Test
    fun `모든 빈 칸이 열리면 플레이어가 승리한 보드가 된다`() {
        val cells =
            mapOf(
                closedEmptyCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        C 2
//        * *
        var board: Board
        board = PlayableBoard(cells)

        board = board.open(0, 0)

        board.shouldBeTypeOf<PlayerWonBoard>()
    }
}
