package minesweeper.application

import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.closedEmptyCellOf
import minesweeper.domain.PlayableGame
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MinesweeperServiceTest {
    @Test
    fun `새로운 지뢰 찾기 게임을 생성한다`() {
        val command = GenerateMinesweeperCommand(height = 1, width = 2, mineCount = 0)
        val generator = StubMinesweeperGenerator.from(closedEmptyCellOf(y = 0, x = 1))
        val service = MinesweeperService(generator)

        val game = service.newGame(command)

        game.shouldBeInstanceOf<PlayableGame>()
    }
}
