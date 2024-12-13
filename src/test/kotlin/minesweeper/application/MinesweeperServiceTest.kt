package minesweeper.application

import io.kotest.matchers.collections.shouldContainAll
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MinesweeperServiceTest {
    @Test
    fun `지뢰 찾기 보드를 생성한다`() {
        val generator =
            StubMinesweeperGenerator.from(
                MinedCell(0, 0),
                EmptyCell(0, 1),
                EmptyCell(1, 0),
                EmptyCell(1, 1),
            )
        val service = MinesweeperService(generator)
        val command = GenerateMinesweeperCommand(height = 2, width = 2, mineCount = 1)

        val board = service.generateBoard(command)

        board.cells shouldContainAll
            setOf(
                MinedCell(0, 0),
                EmptyCell(0, 1),
                EmptyCell(1, 0),
                EmptyCell(1, 1),
            )
    }
}
