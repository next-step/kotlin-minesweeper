package domain

class MineGenerator(
    override val locations: List<Int>,
    override val row: Row
) : CellGenerator {

    override fun generate(): List<Cell> {
        return locations.map {
            Mine.from(it / row.value + 1, it % row.value + 1)
        }
    }
}
