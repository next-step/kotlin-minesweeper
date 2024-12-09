package minesweeper.entity

class Cells private constructor(
    val cells: List<Cell>,
) {
    companion object {
        fun generate(
            height: Height,
            width: Width,
            mineCount: MineCount,
        ): Cells {
            val allCoordinates = generateCoordinates(height, width)
            val mineCoordinates = selectRandomCoordinates(allCoordinates, mineCount)
            val cells = createCells(allCoordinates, mineCoordinates)
            return Cells(cells)
        }

        private fun generateCoordinates(
            height: Height,
            width: Width,
        ): List<Coordinate> {
            return (0 until height.value).flatMap { y ->
                (0 until width.value).map { x -> Coordinate(x, y) }
            }
        }

        private fun selectRandomCoordinates(
            allCoordinates: List<Coordinate>,
            mineCount: MineCount,
        ): Set<Coordinate> {
            validateMineCount(mineCount, allCoordinates.size)
            return allCoordinates.shuffled().take(mineCount.value).toSet()
        }

        private fun validateMineCount(
            mineCount: MineCount,
            totalCells: Int,
        ) {
            require(mineCount.value <= totalCells) { "지뢰 개수는 전체 셀 수를 초과할 수 없습니다." }
        }

        private fun createCells(
            allCoordinates: List<Coordinate>,
            mineCoordinates: Set<Coordinate>,
        ): List<Cell> {
            return allCoordinates.map { coordinate ->
                if (coordinate in mineCoordinates) {
                    Cell.Mine(coordinate)
                } else {
                    Cell.Empty(coordinate)
                }
            }
        }
    }
}
