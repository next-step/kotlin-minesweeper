package minesweeper.controller

import minesweeper.fixture.Board
import minesweeper.model.board.Board
import minesweeper.model.cell.Cell
import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Position
import minesweeper.view.output.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperTest {

    @Test
    fun `컨트롤러 Headless 테스트`() {

        // given
        val boardArea = BoardArea.of(10, 10)
        val expectedOutput = "*".repeat(boardArea.cellCount)

        // 10* 10 , board filled with mines
        val board = Board(boardArea) { _, _ -> true }
            .apply { this.openCell(Position(0, 0)) }

        var actualOutput = ""
        val outputView = object : OutputView {

            override fun printBoard(board: Board) {
                val cells = board.cells
                actualOutput = cells.joinToString(separator = "") { if (it is Cell.Mine) "*" else "C" }
            }
        }

        val controller = MineSweeper(
            boardGenerator = { board },
            inputView = { Position(0, 0) },
            outputView = outputView
        )

        // when
        controller.run()

        // Then
        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
}
