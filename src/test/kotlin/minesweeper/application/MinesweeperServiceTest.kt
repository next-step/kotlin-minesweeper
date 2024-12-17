package minesweeper.application

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.closedEmptyCellOf
import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell
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
//    * C
//    C C
        val service = MinesweeperService(generator)

        val game = service.newGame(command)

        game.shouldBeInstanceOf<PlayableGame>()
        assertSoftly {
            assertCellsAreCorrect(
                game.board,
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
            board.cells[it].shouldBeInstanceOf<MinedCell>()
        }
        empty.forEach {
            board.cells[it].shouldBeInstanceOf<EmptyCell>()
        }
    }
}
