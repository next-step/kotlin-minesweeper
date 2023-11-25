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
        mineGenerator.generate(height, width, mineCount).let(gameBoard::plantMines)
        userInterface.printGameBoard(gameBoard.calculateMineCount())
    }
}

fun main() {
    MinesWeeper().start()
}
