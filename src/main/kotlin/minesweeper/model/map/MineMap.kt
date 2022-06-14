package minesweeper.model.map

import minesweeper.model.map.coordinate.MapSize
import minesweeper.model.map.coordinate.Position

class MineMap private constructor(val mapSize: MapSize, private val cellList: List<Cell>) : List<Cell> by cellList {

    val cellCount: Int by lazy { this.count() }
    val mineCount: Int by lazy { this.count { it is Cell.MineCell } }
    val safeCellCount: Int by lazy { this.count { it is Cell.SafeCell } }

    companion object {

        fun build(mapSize: MapSize, isMineCell: (Position) -> Boolean): MineMap {
            val cells = List(mapSize.area) { index ->
                val position = mapSize[index]

                if (isMineCell(position)) {
                    Cell.MineCell(position)
                } else {
                    Cell.SafeCell(position)
                }
            }
            return MineMap(mapSize, cells)
        }

        fun randomMap(mapSize: MapSize, mineCount: Int): MineMap {
            require(mineCount in 1..mapSize.area)
            val mineIndices = (0 until mapSize.area).shuffled().subList(0, mineCount)
            return build(mapSize) { position -> mapSize.indexOf(position) in mineIndices }
        }
    }
}
