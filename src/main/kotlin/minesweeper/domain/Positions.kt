package minesweeper.domain

class Positions(private val positions: HashSet<Position>) {

    fun generateGroundWithMine(markers: MutableList<List<String>>): MutableList<List<String>> {
        val markersWithMine = markers.toMutableList()

        positions.forEach {
            val column = it.column

            val rowOfMarkers = markersWithMine[column].toMutableList()
            rowOfMarkers[it.row] = MINE_EXPRESSION

            markersWithMine[column] = rowOfMarkers
        }

        return markersWithMine
    }

    companion object {
        private const val MINE_EXPRESSION = "* "
    }
}
