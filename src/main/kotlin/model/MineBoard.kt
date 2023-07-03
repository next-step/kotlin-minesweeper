package model

import model.minemark.Mine
import model.minemark.MineMark
import model.minemark.Safety

data class MineBoard(val elements: BoardElements) {

    val size: Int by lazy { elements.size }
    val maxXPosition: Int by lazy { elements.maxXPosition }
    val maxYPosition: Int by lazy { elements.maxYPosition }

    init {
        require(isAllFilled(elements)) {
            "elements must be filled with all positions. but provided `$elements`"
        }
    }

    fun contains(position: Position): Boolean {
        return elements.contains(position)
    }

    fun contains(position: Position, mark: MineMark): Boolean {
        validateContainsPosition(position)
        return elements.contains(position, mark)
    }

    fun replacedMark(position: Position, mark: MineMark): MineBoard {
        validateContainsPosition(position)
        return MineBoard(elements.replacedPositionMark(position, mark))
    }

    fun doesNotContainsMark(mark: MineMark): Boolean {
        return elements.doesNotContainsMark(mark)
    }

    fun replacedOnlySafetyMarks(countByPosition: (Position) -> (Int)): MineBoard {
        return MineBoard(
            elements.marksMap { position, mark ->
                if (mark == Safety) {
                    mark.next(countByPosition(position))
                } else {
                    mark
                }
            }
        )
    }

    fun mineCount(positions: Collection<Position>): Int {
        return positions.count { contains(it, Mine) }
    }

    private fun isAllFilled(elements: BoardElements): Boolean {
        return zeroToRange(maxXPosition)
            .flatMap { x -> positions(x, maxYPosition) }
            .all { it in elements }
    }

    private fun positions(
        x: Int,
        maxY: Int,
    ): Collection<Position> {
        return zeroToRange(maxY)
            .map { y -> Position(x, y) }
    }

    private fun zeroToRange(count: Int): IntRange = (0..count)

    private fun validateContainsPosition(position: Position) {
        if (contains(position).not()) {
            throw IllegalArgumentException("position must be in elements. elements($this), position(`$position`)")
        }
    }
}
