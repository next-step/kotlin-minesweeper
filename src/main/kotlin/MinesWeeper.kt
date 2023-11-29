import business.Board
import business.GameStatus
import business.MineRandomPointGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(private val userInterface: UserInterface = ConsoleUserInterface()) {
    fun start() {
        val board = initBoard()
        val status = continueGame(board)
        if (status.isWin()) userInterface.printWin()
        else userInterface.displayGameOver(board)
    }

    private fun continueGame(board: Board): GameStatus {
        var status = openPoint(board)
        userInterface.displayOpenResult(board)
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
