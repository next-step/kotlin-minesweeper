package minesweeper.domain

interface LandminePlanter {
    fun plant(location: Location): Landmine

    fun plantAll(
        allCells: List<Cell>,
        landmineCandidates: List<Location>,
    ): List<Cell>
}
