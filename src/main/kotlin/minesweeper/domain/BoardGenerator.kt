package minesweeper.domain

class BoardGenerator(private val mineSpawner: MineSpawner = RandomMineSpawner) {

    fun generate(boardSize: BoardSize, mineCount: MineCount): Board {
        validateMineCount(boardSize, mineCount)

        val mineCoordinates = mineSpawner.spawn(boardSize, mineCount)

        return Board(generateCells(boardSize, mineCoordinates))
    }

    private fun validateMineCount(boardSize: BoardSize, mineCount: MineCount) {
        val maxCellCount = boardSize.height * boardSize.width
        require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
    }

    private fun generateCells(boardSize: BoardSize, mineCoordinates: Coordinates): List<Cell> {
        return Coordinates.coordinatesInArea(boardSize.height, boardSize.width).map { coordinate ->
            if (coordinate in mineCoordinates) {
                Cell.Mine(coordinate)
            } else {
                val aroundMineCount = coordinate.aroundCoordinates().containsCount(mineCoordinates)
                Cell.None(coordinate, aroundMineCount)
            }
        }
    }
}
