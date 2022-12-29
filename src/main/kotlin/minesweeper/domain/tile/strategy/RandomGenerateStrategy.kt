package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.state.Tile

class RandomGenerateStrategy(override val width: Position, override val height: Position, override val mineCount: MineCount) : GenerateStrategy {
    override fun generate(): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var mountCount = mineCount.value

        for (x in 0..width.value) for (y in 0..height.value) {
            val coordinate = Coordinate(Position(x), Position(y))
            tiles.add(Tile.of(coordinate, mountCount--))
        }
        return tiles.shuffled().toList()
    }
}
