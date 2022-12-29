package domain

class CellGenerator {
    fun generate(mineLocations: Locations, BlankLocations: Locations, row: Row): List<Cell> {
        val mineCells = makeMines(mineLocations, row)
        val blankCells = makeBlanks(BlankLocations, row)

        return mineCells + blankCells
    }

    private fun makeMines(locations: Locations, row: Row): List<Mine> {
        return locations.values.map {
            Mine.of(it / row.value + 1, it % row.value + 1)
        }
    }

    private fun makeBlanks(locations: Locations, row: Row): List<Blank> {
        return locations.values.map {
            Blank.of(it / row.value + 1, it % row.value + 1)
        }
    }
}
