package minesweeper.domain

class MineGenerator(private val marker: Marker) {

    fun generateMinePositions(generator: PositionGenerator, countOfMine: Int): HashSet<Position> {
        val positions = HashSet<Position>()

        while (positions.size < countOfMine) {
            positions.add(marker.generateMinePosition(generator))
        }

        return positions
    }
}
