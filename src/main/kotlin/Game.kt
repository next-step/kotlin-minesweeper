import minesweeper.AdminBoard
import minesweeper.MinesweeperBoard
import minesweeper.PlayStatus
import minesweeper.PlayerBoard
import minesweeper.board.BoardDimensions
import minesweeper.board.GameBoardRenderStrategy
import minesweeper.board.Number
import minesweeper.board.RenderedGameBoard
import minesweeper.mine.MineGenerator
import minesweeper.position.Position
import minesweeper.position.RandomPosition
import view.Input
import view.Output

fun main() {
    val defaultGameBoardRender = GameBoardRenderStrategy { boardDimensions, default ->
        RenderedGameBoard(Array(boardDimensions.height.value) { Array(boardDimensions.width.value) { default } })
    }

    Output.printHeightMessage()
    val number: Number = Number(Input.getLine())

    Output.printWidthMessage()
    val width: Number = Number(Input.getLine())

    Output.printMinesMessage()
    val mineCount: Number = Number(Input.getLine())

    val dimensions = BoardDimensions(number, width)

    val mines = MineGenerator(mineCount, RandomPosition(dimensions)).generate()
    val admin = AdminBoard(defaultGameBoardRender, dimensions, mines)
    val player = PlayerBoard(defaultGameBoardRender, dimensions, (number * width) - mineCount)

    val minesweeperBoard = MinesweeperBoard(admin, player, dimensions)

    Output.printStartMessage()
    minesweeperGameStart(dimensions, minesweeperBoard)
}

tailrec fun minesweeperGameStart(dimensions: BoardDimensions, minesweeperBoard: MinesweeperBoard) {
    val position = convertToPosition(dimensions)
    when (play(minesweeperBoard, position)) {
        PlayStatus.OPEN -> {
            Output.printAny(minesweeperBoard.playerBoardRender())
            minesweeperGameStart(dimensions, minesweeperBoard)
        }
        PlayStatus.LOSE -> Output.printLoseGame()
        PlayStatus.WIN -> Output.printWinGame()
    }
}

private tailrec fun convertToPosition(dimensions: BoardDimensions): Position {
    Output.printCellMessage()
    return dimensions.stringToPosition(Input.getLine()) ?: convertToPosition(dimensions)
}

private fun play(board: MinesweeperBoard, position: Position): PlayStatus =
    board.openCell(position)
