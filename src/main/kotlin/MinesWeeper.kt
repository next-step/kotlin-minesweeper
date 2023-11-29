import business.Board
import business.GameStatus
import business.MineRandomPointGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
) {
    fun start() {
        val board = initBoard()
        var status = openPoint(board)
        while (status.isContinue()) {
            displayOpenResult(board)
            status = openPoint(board)
        }
        if (status == GameStatus.WIN) {
            displayWin()
            return
        }
        displayGameOver(board)
    }

    private fun initBoard(): Board {
        val height = userInterface.askHeight()
        val width = userInterface.askWidth()
        val mineCount = userInterface.askMineCount()
        userInterface.printStartAnnouncement()
        return Board.of(height, width, mineCount, MineRandomPointGenerator())
    }

    private fun displayGameOver(board: Board) {
        userInterface.printGameOver()
        board.executeWithMineStatusAndCount({ isMines: Boolean, count: Int ->
            userInterface.printAll(
                isMines,
                count
            )
        }) { userInterface.printNextLine() }.let { userInterface.printNextLine() }
    }

    private fun openPoint(board: Board): GameStatus = board.open(userInterface.askPoint())

    private fun displayOpenResult(board: Board) =
        board.executeWithOpenStatusAndMineCount({ isOpened: Boolean, count: Int ->
            userInterface.printOpenedResult(
                isOpened, count
            )
        }) { userInterface.printNextLine() }.let { userInterface.printNextLine() }

    private fun displayWin() = userInterface.printWin()
}

fun main() {
    MinesWeeper().start()
}
