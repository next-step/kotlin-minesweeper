import business.GameManager
import business.MineRandomGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
) {
    fun start() {
        val (height, width, gameManager) = initGame()
        while (true) {
            val result = openPoint(gameManager)
            if (!result) {
                displayGameOver(height, width, gameManager)
                return
            }
            displayOpenResult(height, width, gameManager)
            if (gameManager.isOver()) {
                displayWin()
                return
            }
        }
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
        userInterface.printMinefieldMatrix(height, width, gameManager.mines)
    }

    private fun openPoint(gameManager: GameManager): Boolean = gameManager.open(userInterface.askPoint())

    private fun displayOpenResult(height: Int, width: Int, gameManager: GameManager) =
        userInterface.printOpenedMinefieldMatrix(height, width, gameManager.openedCells, gameManager.mines)

    private fun displayWin() = userInterface.printWin()
}

fun main() {
    MinesWeeper().start()
}
