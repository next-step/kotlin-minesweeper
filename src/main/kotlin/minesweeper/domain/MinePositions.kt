package minesweeper.domain

import kotlin.random.Random

class MinePositions(rows: Int, cols: Int, mineCount: Int) {
    val minePositions: List<MinePosition>

    init {
        val positions = mutableListOf<MinePosition>()
        repeat(mineCount) {
            var minePosition = MinePosition(Random.nextInt(rows), Random.nextInt(cols))
            while (positions.contains(minePosition)) {
                minePosition = MinePosition(Random.nextInt(rows), Random.nextInt(cols))
            }
            positions.add(minePosition)
        }
        minePositions = positions
    }
}
