package minesweeper.domain.land

import kotlin.math.max
import kotlin.math.min

data class MineData(val landSize: LandSize, val mines: List<Int>) {
    fun countMines(point: Point): Int {
        val yRange = max((point.y - 1), 0)..min((point.y + 1), (landSize.height - 1))
        val xRange = max((point.x - 1), 0)..min((point.x + 1), (landSize.width - 1))

        val surroundInts = ArrayList<Int>()
        yRange.forEach { y ->
            xRange.forEach { x ->
                surroundInts += (y * landSize.width + x)
            }
        }

        return surroundInts.count {
            mines.contains(it)
        }
    }
}
