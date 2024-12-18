package minesweeper.domain

import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.detonatedMineCellOf
import minesweeper.openedEmptyCellOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class CompletedGameTest {
    @Test
    fun `CompletedGame 은 게임이 종료되었다`() {
        PlayerWonGame(
            PlayerWonBoard(
                mapOf(
                    openedEmptyCellOf(y = 0, x = 0),
                ),
            ),
        ).shouldBeInstanceOf<CompletedGame>()

        MineDetonatedGame(
            MineDetonatedBoard(
                mapOf(detonatedMineCellOf(y = 0, x = 0)),
            ),
        ).shouldBeInstanceOf<CompletedGame>()
    }
}
