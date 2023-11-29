import business.Board
import business.GameStatus
import business.MineRandomPointGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(private val userInterface: UserInterface = ConsoleUserInterface()) {
    fun start() {
        val board = initBoard()
        val status = continueGame(board)
        if (status.isWin()) printWin(board)
        else userInterface.displayGameOver(board)
    }

    private fun printWin(board: Board) {
        userInterface.displayOpenResult(board)
        userInterface.printWin()
    }

    private fun continueGame(board: Board): GameStatus {
        var status = openPoint(board)
        while (status.isContinue()) {
            userInterface.displayOpenResult(board)
            status = openPoint(board)
        }
        return status
    }

    private fun initBoard(): Board = Board.of(userInterface.askBoardInfo(), MineRandomPointGenerator())
    private fun openPoint(board: Board): GameStatus = board.open(userInterface.askPoint())
}

fun main() {
    MinesWeeper().start()
}
