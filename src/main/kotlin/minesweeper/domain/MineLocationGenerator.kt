package minesweeper.domain

class MineLocationGenerator(
    private val boardHeight: Int, private val boardWidth: Int,
    private val coordinateGenerator: CoordinateGenerator,
) {
    private val mineLocationValidator = MineLocationValidator()

    fun generateMineLocation(board: Array<Array<Char>>): MineLocation {
        return generateMineLocation1(board)
    }

    private fun generateMineLocation1(board: Array<Array<Char>>): MineLocation {
        val maximumOfX = boardHeight + 1
        val maximumOfY = boardWidth + 1
        var generateMineMineLocation: MineLocation
        do {
            generateMineMineLocation = coordinateGenerator.generateMineLocation(maximumOfX, maximumOfY)
        } while (mineLocationValidator.isDuplicatedMineLocation(board, generateMineMineLocation))
        return generateMineMineLocation
    }

}