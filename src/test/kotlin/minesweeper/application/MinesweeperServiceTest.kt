package minesweeper.application

import io.kotest.matchers.collections.shouldContainAll
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MinesweeperServiceTest {
    @Test
    fun `지뢰 찾기 보드를 생성한다`() {
        val generator =
            StubMinesweeperGenerator.from(
                minedCellOf(0, 0),
                emptyCellOf(0, 1),
                emptyCellOf(1, 0),
                emptyCellOf(1, 1),
            )
        val service = MinesweeperService(generator)
        val command = GenerateMinesweeperCommand(height = 2, width = 2, mineCount = 1)

        val board = service.generateBoard(command)

        board.cells shouldContainAll
            setOf(
                minedCellOf(0, 0),
                emptyCellOf(0, 1),
                emptyCellOf(1, 0),
                emptyCellOf(1, 1),
            )
    }

    private fun minedCellOf(
        y: Int,
        x: Int,
    ) = MinedCell(Coordinate(y, x))

    private fun emptyCellOf(
        y: Int,
        x: Int,
    ) = EmptyCell(Coordinate(y, x))
}
