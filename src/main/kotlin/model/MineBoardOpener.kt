package model

import model.minemark.MineCount
import model.minemark.MineMark

@JvmInline
value class MineBoardOpener(private val countedMineBoard: CountedMineBoard) {

    fun opened(position: Position): CountedMineBoard {
        val mark: MineMark = countedMineBoard.mark(position)
        if (mark.isOpened) {
            return countedMineBoard
        }
        if (mark == BONUS_OPEN_MARK) {
            return countedMineBoard.opened(positionsWhileNearByMineCount(position))
        }
        return countedMineBoard.opened(listOf(position))
    }

    private fun positionsWhileNearByMineCount(position: Position): Collection<Position> {
        val positions: MutableSet<Position> = mutableSetOf(position)
        addNearByPositions(position, positions)
        return positions
    }

    private fun addNearByPositions(position: Position, positions: MutableSet<Position>) {
        position.nearByPositions(countedMineBoard.maxXPosition, countedMineBoard.maxYPosition)
            .filter { positions.contains(it).not() }
            .filter { countedMineBoard.mark(it) is MineCount }
            .onEach { positions.add(it) }
            .forEach {
                if (countedMineBoard.mark(it) == BONUS_OPEN_MARK) {
                    addNearByPositions(it, positions)
                }
            }
    }

    companion object {
        private val BONUS_OPEN_MARK = MineCount(0)
    }
}
