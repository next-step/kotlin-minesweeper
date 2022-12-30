package domain

class AdjacentBlanks(val cells: List<Blank>) {
    fun open() {
        cells.forEach { it.open() }
    }
}
