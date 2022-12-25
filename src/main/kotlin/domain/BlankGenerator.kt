package domain

class BlankGenerator(
    override val locations: List<Int>,
    override val row: Row
) : CellGenerator {
    override fun generate(): List<Cell> {
        return locations.map {
            Blank.from(it / row.value + 1, it % row.value + 1)
        }
    }
}
