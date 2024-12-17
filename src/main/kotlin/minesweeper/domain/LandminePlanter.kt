package minesweeper.domain

interface LandminePlanter {
    fun plant(location: Location): Landmine

    fun plantAll(
        allCells: Cells,
        landmineCandidates: List<Location>,
    ): Cells
}
