package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.tile.Tile
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.state.set.Mine
import minesweeper.domain.tile.state.set.NotChecked

class RandomGenerateStrategy(override val width: Position, override val height: Position, override val mineCount: MineCount) : GenerateStrategy {
    override fun generate(): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var mountCount = mineCount.value

        for (x in 0..width.value) for (y in 0..height.value) {
            val coordinate = Coordinate(Position(x), Position(y))
            tiles.add(mountCount--.let { if (it > 0) Mine(coordinate) else NotChecked(coordinate, false) })
        }
        return tiles.shuffled().toList()
    }
}
