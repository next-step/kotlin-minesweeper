package minesweeper.controller

import minesweeper.domain.Point
import minesweeper.domain.spot.DefaultSpot
import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

class Land private constructor(val spots: Array<Spot>, val height: Int, val width: Int) {
    fun getLines(y: Int): Array<Spot> {
        val startIndex = y * width
        return spots.sliceArray(startIndex..<(startIndex + width))
    }

    companion object {
        fun from(
            height: Int,
            width: Int,
            mineCount: Int,
        ): Land {
            check(height > 0) { "높이를 양수로 입력해 주세요" }
            check(width > 0) { "너비를 양수로 입력해 주세요" }
            check(mineCount > 0) { "지뢰는 한개 이상 입력해주세요" }

            val mines =
                (0..<height * width)
                    .shuffled()
                    .take(mineCount)

            val spots =
                Array(height * width) { index ->
                    val y = index / width
                    val x = index % width
                    when (mines.contains(y * width + x)) {
                        true -> MineSpot(Point(x, y))
                        false -> DefaultSpot(Point(x, y))
                    }
                }

            return Land(spots, height, width)
        }
    }
}
