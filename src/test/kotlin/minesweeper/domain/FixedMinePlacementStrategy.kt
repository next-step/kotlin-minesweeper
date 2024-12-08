package minesweeper.domain

class FixedMinePlacementStrategy(
    private val minePlace: List<Int>
) : MinePlacementStrategy {
    override fun placeMines(height: Int, width: Int, mineCount: Int): List<Int> {
        return minePlace
    }
}
