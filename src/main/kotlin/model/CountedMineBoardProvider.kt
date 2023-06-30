package model

import model.minemark.MineCount

data class CountedMineBoardProvider(private val installMineBoard: InstalledMineBoard) {

    val countedMineBoard: CountedMineBoard by lazy {
        CountedMineBoard(
            installMineBoard.replacedSafetyMark {
                MineCount(installMineBoard.mineCounts(soughtPositions(it)))
            }
        )
    }

    private fun soughtPositions(center: Position): Collection<Position> {
        val xSoughtValues = soughtValues(center.x, installMineBoard.maxXPosition)

        return soughtValues(center.y, installMineBoard.maxYPosition)
            .flatMap { y -> positions(xSoughtValues, y) }
            .filter { it != center }
    }

    private fun positions(xSoughtValues: Collection<Int>, y: Int): List<Position> {
        return xSoughtValues.map { x -> Position(x, y) }
    }

    private fun soughtValues(value: Int, maxValue: Int): Collection<Int> {
        return listOf(value - FOUND_STEP, value, value + FOUND_STEP)
            .filter { (0 <= it) and (it <= maxValue) }
    }

    companion object {
        private const val FOUND_STEP = 1
    }
}
