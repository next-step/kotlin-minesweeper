package minesweeper.entity

class Cells private constructor(
    val cells: List<Cell>,
) {
    fun findCell(coordinate: Coordinate): Cell {
        return cells.find { it.matches(coordinate) }
            ?: throw IllegalArgumentException("셀을 찾을 수 없습니다: $coordinate")
    }

    companion object {
        fun generate(
            height: Height,
            width: Width,
            mineCount: MineCount,
            generator: MineGenerator = RandomMineGenerator(),
        ): Cells {
            val allCoordinates = generateCoordinates(height, width)
            val mineCoordinates = generator.generate(allCoordinates, mineCount)
            val cells = createCells(allCoordinates, mineCoordinates)
            return Cells(cells)
        }

        private fun generateCoordinates(
            height: Height,
            width: Width,
        ): List<Coordinate> {
            return List(height.value * width.value) {
                Coordinate(it % width.value, it / width.value)
            }
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
