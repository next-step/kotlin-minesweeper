package minesweeper.domain

class BoardGenerator(private val mineSpawner: MineSpawner = RandomMineSpawner) {

    fun generate(area: Area, mineCount: MineCount): Board {
        validateMineCount(area, mineCount)

        val mineCoordinates = mineSpawner.spawn(area, mineCount)

        return Board(generateCells(area, mineCoordinates))
    }

    private fun validateMineCount(area: Area, mineCount: MineCount) {
        val maxCellCount = area.height * area.width
        require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
    }

    private fun generateCells(area: Area, mineCoordinates: Coordinates): List<Cell> {
        return Coordinates.coordinatesInArea(area).map { coordinate ->
            if (coordinate in mineCoordinates) {
                Cell.Mine(coordinate)
            } else {
                val aroundMineCount = coordinate.aroundCoordinates().containsCount(mineCoordinates)
                Cell.None(coordinate, aroundMineCount)
            }
        }
    }
}
