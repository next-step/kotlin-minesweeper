package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.closedEmptyCellOf
import minesweeper.detonatedMineCellOf
import minesweeper.openedEmptyCellOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GamesTest {
    @Test
    fun `PlayableGame 은 게임이 종료되지 않았다`() {
        val game =
            PlayableGame(
                PlayableBoard(
                    mapOf(closedEmptyCellOf(0, 0)),
                ),
            )
        game.isOver shouldBe false
    }

    @Test
    fun `CompletedGame 은 게임이 종료되었다`() {
        PlayerWonGame(
            PlayerWonBoard(
                mapOf(openedEmptyCellOf(0, 0)),
            ),
        ).isOver shouldBe true

        MineDetonatedGame(
            MineDetonatedBoard(
                mapOf(detonatedMineCellOf(0, 0)),
            ),
        ).isOver shouldBe true
    }
}
