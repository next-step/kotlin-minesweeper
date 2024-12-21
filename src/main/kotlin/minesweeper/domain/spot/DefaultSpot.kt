package minesweeper.domain.spot

import minesweeper.domain.land.MineData
import minesweeper.domain.land.Point

class DefaultSpot(point: Point, mineData: MineData) : Spot(point) {
    val surroundMineCount: Int = mineData.countMines(point)
}
