package minesweeper.domain

class MinesWeeperGameBoard(
    private val height: Int,
    private val width: Int,
    mineNumber: Int
) {
    private var board: Array<Array<Char>> = Array(height) { Array(width) { 'C' } }
    private val mineLocationGenerator = MineLocationGenerator(height, width, RandomMineLocationCoordinateGenerator())

    init {
        generateMines(mineNumber)
    }

    private fun generateMines(mineNumber: Int) {
        repeat(mineNumber) {
            val generatedMineLocation = mineLocationGenerator.generateMineLocation(getBoard())
            insertMine(generatedMineLocation)
        }
    }

//    private fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): MineLocation {
//        var generateMineMineLocation: MineLocation
//        do {
//            generateMineMineLocation = mineLocationCoordinateGenerator.generateMineLocation(maximumOfX, maximumOfY)
//        } while (mineLocationValidator.isDuplicatedMineLocation(getBoard(), generateMineMineLocation))
//        return generateMineMineLocation
//    }

    private fun insertMine(mineLocation: MineLocation) {
        val (x, y) = mineLocation
        board[x][y] = '*'
    }

    fun getBoard(): Array<Array<Char>> {
        return Array(height) { i -> Array(width) { j -> board[i][j] } }
    }
}
