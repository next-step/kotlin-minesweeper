import business.MineGenerator
import business.MineRandomGenerator
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
        userInterface.printMinefieldMatrix(height, width, mines)
    }
}

fun main() {
    MinesWeeper().start()
}
