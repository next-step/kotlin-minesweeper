package minesweeper.domain.tile.strategy

import minesweeper.domain.MineCount
import minesweeper.domain.tile.pos.Coordinate
import minesweeper.domain.tile.pos.Position
import minesweeper.domain.tile.state.Tile

class RandomGenerateStrategy(override val width: Position, override val height: Position, override val mineCount: MineCount) : GenerateStrategy {
    override fun generate(): List<Tile> {
        val tiles = mutableListOf<Tile>()
        var mineList = mutableListOf<Boolean>()
        var mineCount = mineCount.value

        for (index in 0 until width.getCalibratedPosition() * height.getCalibratedPosition()) {
            addMine(mineCount--, mineList)
        }
        mineList = mineList.shuffled().toMutableList()

        for (x in 0..width.value) for (y in 0..height.value) {
            val coordinate = Coordinate(Position(x), Position(y))
            tiles.add(Tile.of(coordinate, mineList.removeAt(0)))
        }
        return tiles.toList()
    }

    private fun addMine(mineCount: Int, mineList: MutableList<Boolean>) {
        if (mineCount > 0) {
            mineList.add(true)
            return
        }
        mineList.add(false)
    }
}
