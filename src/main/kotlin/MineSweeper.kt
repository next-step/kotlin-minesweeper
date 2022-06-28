import domain.Matrix
import domain.geometric.Dimension
import ui.input.InputUI
import ui.output.OutputUI
import ui.output.dto.MatrixDto

object MineSweeper {

    fun run() {
        val width = InputUI.readWidth()
        val height = InputUI.readHeight()
        val numberOfMines = InputUI.readNumberOfMines()

        val dimension = Dimension(width = width, height = height)

        val matrix = Matrix.of(dimension, numberOfMines)

        OutputUI.showBoard(MatrixDto.from(matrix))
    }
}
