import minesweeper.BoardDimensions
import minesweeper.CellOpenStatus
import minesweeper.GameBoardRenderStrategy
import minesweeper.Height
import minesweeper.MineCount
import minesweeper.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.RandomPosition
import minesweeper.RenderedGameBoard
import minesweeper.Width
import view.Input
import view.Output

fun main() {
    val defaultGameBoardRender = GameBoardRenderStrategy { boardDimensions, default ->
        RenderedGameBoard(Array(boardDimensions.height.value) { Array(boardDimensions.width.value) { default } })
    }

    Output.printHeightMessage()
    val height: Height = Height(Input.getLine())

    Output.printWidthMessage()
    val width: Width = Width(Input.getLine())

    Output.printMinesMessage()
    val mineCount: MineCount = MineCount(Input.getLine())

    val dimensions = BoardDimensions(height, width)

    val mines = MineGenerator(mineCount, RandomPosition(dimensions)).generate()
    val minesweeperBoard = MinesweeperBoard(defaultGameBoardRender, dimensions, mines)

    Output.printStartMessage()
    do {
        Output.printCellMessage()
        val result = dimensions.stringToPosition(Input.getLine())
        result.onSuccess {
            if (minesweeperBoard.openCell(it) == CellOpenStatus.FAIL) {
                Output.printLoseGame()
                return
            }
            Output.printAny(minesweeperBoard.playerBoardRender())
        }.onFailure {
            Output.printAny(it.message)
        }
    } while (result.isSuccess)
}
