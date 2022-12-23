package domain

class CellGenerator(
    private val blankLocations: List<Int> = emptyList(),
    private val mineLocations: List<Int> = emptyList(),
    private val row: Row
) {
    fun minesGenerate(): List<Cell> {
        return mineLocations.map {
            Mine.from(it / row.value + 1, it % row.value + 1)
        }
    }

    fun blanksGenerate(): List<Cell> {
        return blankLocations.map {
            Blank.from(it / row.value + 1, it % row.value + 1)
        }
    }
}
