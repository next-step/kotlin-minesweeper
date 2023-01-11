package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.land.state.Area
import minesweeper.domain.tile.state.Tile

interface GenerateStrategy {
    val area: Area

    val mineCount: MineCount

    fun generate(): List<Tile>
}
