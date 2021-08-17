package minesweeper

object MineGenerator {
    fun generateMinePosition(generator: PositionGenerator, ground: Ground): Position {
        return Position(generator.generate(ground.height), ground.vertical)
    }
}
