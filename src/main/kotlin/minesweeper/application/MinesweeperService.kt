package minesweeper.application

class MinesweeperService(
    private val boardGenerator: BoardGenerator,
) {
    fun generateBoard(command: GenerateMinesweeperCommand) = boardGenerator.generate(command)
}
