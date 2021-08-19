package minesweeper.domain

class MineGenerator(private val ground: Ground) {

    fun generateMinePositions(generator: PositionGenerator, countOfMine: Int): HashSet<Position> {
        val positions = HashSet<Position>()

        while (positions.size < countOfMine) {
            positions.add(generateMinePosition(generator))
        }
        return positions
    }

    private fun generateMinePosition(generator: PositionGenerator): Position {
        return Position(generator.generate(ground.height), generator.generate(ground.vertical))
    }
}
