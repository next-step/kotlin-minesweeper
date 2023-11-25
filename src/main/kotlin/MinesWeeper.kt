class MinesWeeper(
    private val userInterface: UserInterface = ConsoleUserInterface(),
    private val mineGenerator: MineGenerator = MineRandomGenerator(),
) {
    fun start() {
        val height = userInterface.askHeight()
        val width = userInterface.askWidth()
        val gameBoard = GameBoard.of(height, width)
        val mineCount = userInterface.askMineCount()
        userInterface.printStartAnnouncement()
        mineGenerator.generatePoint(height, width, mineCount).let(gameBoard::plantMines)
        userInterface.printMinefieldMatrix(gameBoard.calculateMineCount().getMap())
    }
}

fun main() {
    MinesWeeper().start()
}
