package model

import model.minemark.MineCount
import model.minemark.MineMark

@JvmInline
value class MineBoardOpener(private val countedMineBoard: CountedMineBoard) {

    fun openedCountedMineBoard(position: Position): CountedMineBoard {
        val mark: MineMark = countedMineBoard.mark(position)
        if (mark.isOpened) {
            return countedMineBoard
        }
        return countedMineBoard.openedMineBoard(positionsToOpen(position))
    }

    private fun positionsToOpen(
        position: Position,
        positionsToOpen: Collection<Position> = setOf(position),
    ): Collection<Position> {
        if (countedMineBoard.mark(position) == BONUS_OPEN_MARK) {
            return nearByPositionsToOpen(position, positionsToOpen)
        }
        return setOf(position)
    }

    private fun nearByPositionsToOpen(position: Position, positionsToOpen: Collection<Position>): Collection<Position> {
        val nearByPositions = position.nearByPositions(countedMineBoard.maxXPosition, countedMineBoard.maxYPosition)
            .filter { positionsToOpen.contains(it).not() }
            .filter { countedMineBoard.isMineCount(it) }

        val nextPositionsToOpen: MutableSet<Position> = (positionsToOpen + nearByPositions).toMutableSet()
        nearByPositions.forEach {
            nextPositionsToOpen.addAll(positionsToOpen(it, nextPositionsToOpen))
        }
        return nextPositionsToOpen
    }

    companion object {
        private val BONUS_OPEN_MARK = MineCount(0)
    }
}
