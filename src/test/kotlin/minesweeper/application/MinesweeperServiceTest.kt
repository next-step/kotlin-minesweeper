package minesweeper.application

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell
import minesweeper.emptyCellOf
import minesweeper.minedCellOf
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class MinesweeperServiceTest {
    @Test
    fun `지뢰 찾기 보드를 생성한다`() {
        val generator =
            StubMinesweeperGenerator.from(
                minedCellOf(y = 0, x = 0),
                emptyCellOf(y = 0, x = 1),
                emptyCellOf(y = 1, x = 0),
                emptyCellOf(y = 1, x = 1),
            )
        val service = MinesweeperService(generator)
        val command = GenerateMinesweeperCommand(height = 2, width = 2, mineCount = 1)

        val board = service.generateBoard(command)

        assertSoftly {
            assertCellsAreCorrect(
                board,
                mined = listOf(Coordinate(y = 0, x = 0)),
                empty =
                    listOf(
                        Coordinate(y = 0, x = 1),
                        Coordinate(y = 1, x = 0),
                        Coordinate(y = 1, x = 1),
                    ),
            )
        }
    }

    private fun assertCellsAreCorrect(
        board: Board,
        mined: List<Coordinate>,
        empty: List<Coordinate>,
    ) {
        val allCoordinates = mined + empty
        board.cells.keys shouldContainExactlyInAnyOrder allCoordinates

        mined.forEach {
            board.cells[it].shouldBeTypeOf<MinedCell>()
        }
        empty.forEach {
            board.cells[it].shouldBeTypeOf<EmptyCell>()
        }
    }
}
