package domain

interface MinePositionsSelectStrategy {

    fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position>
}
