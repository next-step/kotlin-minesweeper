package minesweeper.entity

class MineFieldFactory(
    private val mineGenerator: MineGenerator,
) {
    fun create(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ): MineField {
        validateInputs(height, width, mineCount)

        val allCoordinates = generateCoordinates(height, width)
        val mineCoordinates = mineGenerator.generate(allCoordinates, mineCount)

        val cells = Cells.create(allCoordinates, mineCoordinates)

        return MineField(height, width, cells)
    }

    private fun validateInputs(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ) {
        val totalCells = height.value * width.value
        require(mineCount.value <= totalCells) {
            "지뢰 개수는 전체 셀 수를 초과할 수 없습니다. (total: $totalCells, mineCount: ${mineCount.value})"
        }
    }

    private fun generateCoordinates(
        height: Height,
        width: Width,
    ): List<Coordinate> {
        return List(height.value * width.value) {
            Coordinate(it % width.value, it / width.value)
        }
    }
}
