package minesweeper.domain

object MineGenerator {

    fun generateMinePositions(countOfMine: Int, ground: Ground): HashSet<Position> {
        val positions = HashSet<Position>()

        while (positions.size < countOfMine) {
            positions.add(generateMinePosition(RandomPositionGenerator(), ground))
        }
        return positions
    }

    private fun generateMinePosition(generator: PositionGenerator, ground: Ground): Position {
        return Position(generator.generate(ground.height), generator.generate(ground.vertical))
    }
}
