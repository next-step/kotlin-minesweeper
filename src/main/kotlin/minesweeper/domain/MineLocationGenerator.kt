package minesweeper.domain

class MineLocationGenerator(
    private val coordinateGenerator: CoordinateGenerator,
) {
    private val mineLocationValidator = MineLocationValidator()

    fun generateMineLocation(board: Array<Array<Char>>): MineLocation {
        val maximumOfX = board.first().size
        val maximumOfY = board.size
        var generatedMineLocation: MineLocation
        do {
            val generatedXCoordinate = coordinateGenerator.generateCoordinate(maximumOfX)
            val generatedYCoordinate = coordinateGenerator.generateCoordinate(maximumOfY)
            generatedMineLocation = MineLocation(generatedXCoordinate, generatedYCoordinate)
        } while (mineLocationValidator.validateMineLocation(board, generatedMineLocation))
        return generatedMineLocation
    }
}
