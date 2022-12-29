package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.state.Tile

interface GenerateStrategy {
    val width: Position

    val height: Position

    val mineCount: MineCount

    fun generate(): List<Tile>
}
