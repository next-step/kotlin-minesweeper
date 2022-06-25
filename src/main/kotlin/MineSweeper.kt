import domain.Board
import domain.Matrix
import domain.geometric.Dimension
import ui.input.InputUI
import ui.output.OutputUI
import ui.output.dto.BoardDto

object MineSweeper {

    fun run() {
        val width = InputUI.readWidth()
        val height = InputUI.readHeight()
        val numberOfMines = InputUI.readNumberOfMines()

        val dimension = Dimension(width = width, height = height)

        val board = Board(Matrix.of(dimension, numberOfMines))

        OutputUI.showBoard(BoardDto.from(board))
    }
}
