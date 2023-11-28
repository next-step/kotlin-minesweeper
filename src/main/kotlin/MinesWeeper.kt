import business.GameManager
import business.GameStatus
import business.MineRandomGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
) {
    fun start() {
        val (height, width, gameManager) = initGame()
        var status = openPoint(gameManager)
        while (status.isContinue()) {
            displayOpenResult(height, width, gameManager)
            status = openPoint(gameManager)
        }
        if (status == GameStatus.WIN) displayWin()
        displayGameOver(height, width, gameManager)
    }

    private fun initGame(): Triple<Int, Int, GameManager> {
        val height = userInterface.askHeight()
        val width = userInterface.askWidth()
        val mineCount = userInterface.askMineCount()
        userInterface.printStartAnnouncement()
        val gameManager = GameManager.of(height, width, mineCount, MineRandomGenerator())
        return Triple(height, width, gameManager)
    }

    private fun displayGameOver(height: Int, width: Int, gameManager: GameManager) {
        userInterface.printGameOver()
        gameManager.doActionWithMines { userInterface.printMinefieldMatrix(height, width, it) }
    }

    private fun openPoint(gameManager: GameManager): GameStatus = gameManager.open(userInterface.askPoint())

    private fun displayOpenResult(height: Int, width: Int, gameManager: GameManager) =
        gameManager.doActionWithMinesAndOpenedCells { mines, openedCells ->
            userInterface.printOpenedMinefieldMatrix(height, width, mines, openedCells)
        }

    private fun displayWin() = userInterface.printWin()
}

fun main() {
    MinesWeeper().start()
}
