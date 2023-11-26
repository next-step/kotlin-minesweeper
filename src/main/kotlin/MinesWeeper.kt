import business.MineGenerator
import business.MineRandomGenerator
import business.OpenedCells
import view.ConsoleUserInterface
import view.UserInterface

class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
    private val mineGenerator: MineGenerator = MineRandomGenerator(),
) {
    fun start() {
        val height = userInterface.askHeight()
        val width = userInterface.askWidth()
        val mineCount = userInterface.askMineCount()
        userInterface.printStartAnnouncement()
        val mines = mineGenerator.generate(height, width, mineCount)
        val openedCells = OpenedCells(height, width)
        while (true) {
            val point = userInterface.askPoint()
            if (mines.contains(point)) {
                userInterface.printGameOver()
                userInterface.printMinefieldMatrix(height, width, mines)
                return
            }
            openedCells.add(point, mines)
            userInterface.printOpenedMinefieldMatrix(height, width, openedCells, mines)
            if (openedCells.isAllOpened(mines)) {
                userInterface.printWin()
                return
            }
        }
    }
}

fun main() {
    MinesWeeper().start()
}
