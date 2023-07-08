package model

import model.minemark.Mine
import model.minemark.MineMark

data class MineInstallation(
    private val count: Int,
    private val mark: MineMark = Mine(),
    private val nextPosition: (maxX: Int, maxY: Int) -> Position,
) {
    init {
        require(count > 0) { "install count must be positive. but provided `$count`" }
    }

    fun installedMineBoard(filledElements: FilledElements): InstalledMineBoard {
        validateMineBoardSize(filledElements)
        val positions: Collection<Position> = distinctPositions(filledElements)
        validatePositionContains(filledElements, positions)
        return InstalledMineBoard(
            filledElements.replacedMarkElements(changedMarkIfContains(positions))
        )
    }

    private fun validateMineBoardSize(filledElements: FilledElements) {
        require(count <= filledElements.size) {
            "install count must be less than or equal to board size. mine board size(`${filledElements.size}`), mineCount(`$count`)"
        }
    }

    private fun distinctPositions(filledElements: FilledElements): Collection<Position> {
        val positions: MutableSet<Position> = mutableSetOf()
        while (positions.size < count) {
            positions.add(nextPosition(filledElements.maxXPosition, filledElements.maxYPosition))
        }
        return positions
    }

    private fun validatePositionContains(filledElements: FilledElements, positions: Collection<Position>) {
        check(filledElements.containsAll(positions)) {
            "positions must contain position. but provided positions(`$positions`) and elements(`$filledElements`)"
        }
    }

    private fun changedMarkIfContains(positions: Collection<Position>): (Position, MineMark) -> MineMark {
        return { position, mineMark ->
            if (positions.contains(position)) {
                mark
            } else {
                mineMark
            }
        }
    }
}
