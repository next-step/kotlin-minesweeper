package minesweeper.domain

class MineGenerator(private val marker: Marker) {

    fun generateMinePositions(generator: PositionGenerator, countOfMine: Int): HashSet<Position> {
        val positions = HashSet<Position>()

        while (positions.size < countOfMine) {
            positions.add(generateMinePosition(generator))
        }

        return positions
    }

    private fun generateMinePosition(generator: PositionGenerator): Position {
        return Position(generator.generate(marker.height), generator.generate(marker.vertical))
    }
}
