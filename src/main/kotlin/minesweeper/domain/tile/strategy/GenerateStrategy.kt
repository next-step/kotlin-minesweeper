package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.tile.Tile
import minesweeper.domain.tile.pos.Position

interface GenerateStrategy {
    val width: Position

    val height: Position

    val mineCount: MineCount

    fun generate(): List<Tile>
}
