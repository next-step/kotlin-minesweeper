package minesweeper.controller

import minesweeper.domain.Point
import minesweeper.domain.spot.DefaultSpot
import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

class Land private constructor(val spots: List<Spot>, val height: Int, val width: Int) {
    fun getLines(y: Int): List<Spot> {
        val startIndex = y * width
        return spots.slice(startIndex..<(startIndex + width))
    }

    companion object {
        fun from(
            height: Int,
            width: Int,
            mineCount: Int,
            generateMines: ((Int, Int) -> List<Int>) = { total: Int, count: Int -> GameApp.generateMines(total, count) },
        ): Land {
            val landSize = height * width

            check(landSize > mineCount) { "땅보다 많은 지뢰를 심을 수 없어요" }

            val mines = generateMines.invoke(landSize, mineCount)
            val spots =
                List(landSize) { index ->
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
