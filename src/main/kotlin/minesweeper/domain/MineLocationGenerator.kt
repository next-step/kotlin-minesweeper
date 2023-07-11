package minesweeper.domain

class MineLocationGenerator(
    private val coordinateGenerator: CoordinateGenerator,
) {

    fun generateMineLocation(board: List<List<GameBoardSquare>>): SquareLocation {
        val maximumOfX = board.first().size
        val maximumOfY = board.size
        var generatedSquareLocation: SquareLocation
        do {
            val generatedXCoordinate = coordinateGenerator.generateCoordinate(maximumOfX)
            val generatedYCoordinate = coordinateGenerator.generateCoordinate(maximumOfY)
            generatedSquareLocation = SquareLocation(generatedXCoordinate, generatedYCoordinate)
        } while (MineLocationValidator.validateMineLocation(board, generatedSquareLocation))
        return generatedSquareLocation
    }
}
