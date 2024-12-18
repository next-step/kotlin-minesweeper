package minesweeper.domain

import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.closedEmptyCellOf
import minesweeper.detonatedMineCellOf
import minesweeper.openedEmptyCellOf
import minesweeper.playableGameOf
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class GamesTest {
    @Test
    fun `PlayableGame 은 게임이 종료되지 않았다`() {
        val game = playableGameOf(closedEmptyCellOf(y = 0, x = 0))
        game.isOver shouldBe false
    }

    @Test
    fun `CompletedGame 은 게임이 종료되었다`() {
        PlayerWonGame(
            PlayerWonBoard(
                mapOf(
                    openedEmptyCellOf(y = 0, x = 0),
                ),
            ),
        ).isOver shouldBe true

        MineDetonatedGame(
            MineDetonatedBoard(
                mapOf(detonatedMineCellOf(y = 0, x = 0)),
            ),
        ).isOver shouldBe true
    }

    @Test
    fun `좌표가 판의 범위를 벗어날 수 없다`() {
        val game = playableGameOf(closedEmptyCellOf(y = 0, x = 0))
        assertThrows<IllegalArgumentException> { game.open(y = 1, x = 0) }
    }

    @Test
    fun `이미 열린 칸은 또 열 수 없다`() {
        val game = playableGameOf(openedEmptyCellOf(y = 0, x = 0))
        assertThrows<IllegalStateException> { game.open(y = 0, x = 0) }
    }

    @Test
    fun `지뢰가 있는 칸을 열면 게임이 종료된다`() {
        var game: Game
        game =
            playableGameOf(
                detonatedMineCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
            )
        game = game.open(y = 0, x = 0)

        game.shouldBeTypeOf<MineDetonatedGame>()
    }

    @Test
    fun `지정한 좌표의 칸을 열 수 있다`() {
        var game: Game
        game =
            playableGameOf(
                closedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 0, x = 2),
            )

        game = game.open(y = 0, x = 0)

        val board = game.board as PlayableBoard
        board.get(y = 0, x = 0) shouldBe OpenedEmptyCell
    }

    @Test
    fun `모든 칸이 열리기 전에는 게임 진행 상태이다`() {
        var game: Game
        game =
            playableGameOf(
                closedEmptyCellOf(y = 0, x = 0),
                undetonatedMineCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 0, x = 2),
            )

        game = game.open(y = 0, x = 0)

        game.shouldBeTypeOf<PlayableGame>()
    }

    @Test
    fun `칸에 주변에 지뢰가 있는 경우 해당 칸만 열린다`() {
        var game: Game
        game =
            playableGameOf(
                closedEmptyCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        C C
//        * *

        game = game.open(0, 0)

        game.board.cells shouldContainExactly
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
    fun `인접한 지뢰가 없는 칸을 열면 주변의 칸도 열린다`() {
        var game: Game
        game =
            playableGameOf(
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
//        C C *
//        C C C
//        C C *

        game = game.open(0, 0)

        game.shouldBeTypeOf<PlayableGame>()
        game.board.cells shouldContainExactly
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
    fun `모든 칸이 열리면 플레이어가 승리한다`() {
        var game: Game
        game =
            playableGameOf(
                closedEmptyCellOf(y = 0, x = 0),
                openedEmptyCellOf(y = 0, x = 1),
                undetonatedMineCellOf(y = 1, x = 0),
                undetonatedMineCellOf(y = 1, x = 1),
            )
//        C 2
//        * *

        game = game.open(0, 0)
//        2 2
//        * *

        game.shouldBeTypeOf<PlayerWonGame>()
    }
}
