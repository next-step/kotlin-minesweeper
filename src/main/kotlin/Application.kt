import model.Board
import model.BoardHeight
import model.BoardWidth
import model.Mine
import model.MineSize
import model.Judge
import view.InputView
import view.OutputView

fun main() {
    val boardHeight = BoardHeight(InputView.getHeight())
    val boardWidth = BoardWidth(InputView.getWidth())
    val mineSize = MineSize(InputView.getMineNumber())
    val mines = Mine.generateMines(boardHeight, boardWidth, mineSize)
    val board = Board.createBoard(boardHeight, boardWidth, mines)

    do {
        val coordinates = InputView.getCoordinate()
        val judge = Judge(board, boardWidth.width, boardHeight.height)
        val selectedSquareType = judge.clickSquare(coordinates.first, coordinates.second)

        OutputView.showBoard(judge.squares.flatten(), boardWidth.width)
    } while (OutputView.showLoseGame(selectedSquareType))
}
