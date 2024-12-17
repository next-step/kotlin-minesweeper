package minesweeper.domain

class Vulture : LandminePlanter {
    override fun plant(location: Location): Landmine = Landmine(location)

    override fun plantAll(
        allCells: List<Cell>,
        landmineCandidates: List<Location>,
    ): List<Cell> {
        return allCells.map { cell ->
            if (cell.location() in landmineCandidates && cell is BasicCell) {
                plant(cell.location())
            } else {
                cell
            }
        }
    }
}
