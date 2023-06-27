package minesweeper

class MinesWeeperGameBoard(
    private val height: Int,
    private val width: Int,
    mineNumber: Int,
    private val mineLocationGenerator: MineLocationGenerator
) {
    private var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }
    private val mineLocationValidator = MineLocationValidator()

    init {
        generateMines(mineNumber, width, height)
    }

    private fun generateMines(mineNumber: Int, maximumOfX: Int, maximumOfY: Int) {
        repeat(mineNumber) {
            val generatedMineLoation = generateMineLoation(maximumOfX, maximumOfY)
            insertMine(generatedMineLoation)
        }
    }

    private fun generateMineLoation(maximumOfX: Int, maximumOfY: Int): Location {
        var generateMineLocation: Location
        do {
            generateMineLocation = mineLocationGenerator.generateMineLocation(maximumOfX, maximumOfY)
        } while (mineLocationValidator.isDuplicatedMineLocation(getBoard(), generateMineLocation))
        return generateMineLocation
    }

    private fun insertMine(location: Location) {
        val (x, y) = location
        board[x][y] = '*'
    }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
