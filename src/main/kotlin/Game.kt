import minesweeper.AdminBoard
import minesweeper.board.BoardDimensions
import minesweeper.CellOpenStatus
import minesweeper.board.GameBoardRenderStrategy
import minesweeper.board.Height
import minesweeper.mine.MineCount
import minesweeper.mine.MineGenerator
import minesweeper.MinesweeperBoard
import minesweeper.PlayerBoard
import minesweeper.board.RenderedGameBoard
import minesweeper.position.Position
import minesweeper.position.RandomPosition
import minesweeper.board.Width
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
    val admin = AdminBoard(defaultGameBoardRender, dimensions, mines)
    val player = PlayerBoard(defaultGameBoardRender, dimensions)

    val minesweeperBoard = MinesweeperBoard(admin, player, dimensions)

    Output.printStartMessage()
    do {
        Output.printCellMessage()
        val result = dimensions.stringToPosition(Input.getLine())
        play(minesweeperBoard, result)
    } while (result.isSuccess)
}

private fun play(board: MinesweeperBoard, result: Result<Position>) {
    result.onSuccess {
        printOpenCellResult(board, board.openCell(it))
    }.onFailure {
        Output.printAny(it.message)
    }
}

private fun printOpenCellResult(board: MinesweeperBoard, openCell: CellOpenStatus) {
    when (openCell) {
        CellOpenStatus.SUCCESS -> Output.printAny(board.playerBoardRender())
        CellOpenStatus.FAIL -> Output.printLoseGame()
    }
}
