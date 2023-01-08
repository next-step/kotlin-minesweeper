package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.state.Tile

class MineFirstGenerateStrategy(override val area: Area, override val mineCount: MineCount) : GenerateStrategy {
    override fun generate(): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var mineCount = mineCount.value

        for (x in 0 until area.width) for (y in 0 until area.height) {
            val coordinate = Coordinate(Position(x), Position(y))
            tiles.add(Tile.of(coordinate, mineCount--))
        }
        return tiles.toList()
    }
}
