package minesweeper.entity

class MineFieldFactory(
    private val mineGenerator: MineGenerator,
) {
    fun create(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ): MineField {
        val totalCells = height.value * width.value
        mineCount.validate(totalCells)

        val allCoordinates = Coordinate.generateCoordinates(height, width)
        val mineCoordinates = mineGenerator.generate(allCoordinates, mineCount)

        val cells = Cells.create(allCoordinates, mineCoordinates)

        return MineField(height, width, cells)
    }
}
