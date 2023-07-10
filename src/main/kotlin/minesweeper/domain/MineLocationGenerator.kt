package minesweeper.domain

class MineLocationGenerator(
    private val coordinateGenerator: CoordinateGenerator,
) {

    fun generateMineLocation(board: List<List<GameBoardSquare>>): MineLocation {
        val maximumOfX = board.first().size
        val maximumOfY = board.size
        var generatedMineLocation: MineLocation
        do {
            val generatedXCoordinate = coordinateGenerator.generateCoordinate(maximumOfX)
            val generatedYCoordinate = coordinateGenerator.generateCoordinate(maximumOfY)
            generatedMineLocation = MineLocation(generatedXCoordinate, generatedYCoordinate)
        } while (MineLocationValidator.validateMineLocation(board, generatedMineLocation))
        return generatedMineLocation
    }
}
