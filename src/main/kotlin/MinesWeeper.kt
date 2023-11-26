import business.GameManager
import business.MineRandomGenerator
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
) {
    fun start() {
        val height = userInterface.askHeight()
        val width = userInterface.askWidth()
        val mineCount = userInterface.askMineCount()
        userInterface.printStartAnnouncement()
        val gameManager = GameManager.of(height, width, mineCount, MineRandomGenerator())
        while (true) {
            val point = userInterface.askPoint()
            val result = gameManager.open(point)
            if (!result) {
                userInterface.printGameOver()
                userInterface.printMinefieldMatrix(height, width, gameManager.mines)
                return
            } else {
                userInterface.printOpenedMinefieldMatrix(height, width, gameManager.openedCells(), gameManager.mines)
            }
            if (gameManager.isOver()) {
                userInterface.printWin()
                return
            }
        }
    }
}

fun main() {
    MinesWeeper().start()
}
