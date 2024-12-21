package minesweeper.domain.land

import minesweeper.domain.spot.DefaultSpot
import minesweeper.domain.spot.MineSpot
import minesweeper.domain.spot.Spot

class Land private constructor(val spots: List<Spot>, val land: LandSize) {
    fun getLines(y: Int): List<Spot> {
        val startIndex = y * land.width
        return spots.slice(startIndex..<(startIndex + land.width))
    }

    companion object {
        fun from(
            land: LandSize,
            mineCount: Int,
            generateMines: ((Int, Int) -> List<Int>),
        ): Land {
            val landSize = land.size()
            check(landSize > mineCount) { "땅보다 많은 지뢰를 심을 수 없어요" }

            val mines = generateMines.invoke(landSize, mineCount)
            val spots =
                List(landSize) { index ->
                    val y = index / land.width
                    val x = index % land.width
                    when (mines.contains(y * land.width + x)) {
                        true -> MineSpot(Point(x, y))
                        false -> DefaultSpot(Point(x, y))
                    }
                }

            return Land(spots, land)
        }
    }
}
