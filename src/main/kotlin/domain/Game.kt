package domain

class Game(
    height: Int,
    width: Int,
    mineCount: Int,
    cellMapper: CellMapper = DefaultCellMapper(),
) {
    private val mineField =
        MineField(
            Height(height),
            Width(width),
            mineCount,
            cellMapper,
        )

    fun start() {
        mineField.display().forEach { println(it) }
    }
}
