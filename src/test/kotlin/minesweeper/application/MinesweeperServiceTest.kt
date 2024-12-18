package minesweeper.application

import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.closedEmptyCellOf
import minesweeper.domain.PlayableGame
import minesweeper.undetonatedMineCellOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MinesweeperServiceTest {
    @Test
    fun `새로운 지뢰 찾기 게임을 생성한다`() {
        val command = GenerateMinesweeperCommand(height = 2, width = 2, mineCount = 1)
        val generator =
            StubMinesweeperGenerator.from(
                undetonatedMineCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
            )
        val service = MinesweeperService(generator)

        val game = service.newGame(command)
//        * C
//        C C

        game.shouldBeInstanceOf<PlayableGame>()
        game.board.cells shouldContainExactly
            mapOf(
                undetonatedMineCellOf(y = 0, x = 0),
                closedEmptyCellOf(y = 0, x = 1),
                closedEmptyCellOf(y = 1, x = 0),
                closedEmptyCellOf(y = 1, x = 1),
            )
    }
}
