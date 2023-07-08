package model

import model.minemark.MineMark

data class InstalledMineBoard(
    private val filledElements: FilledElements,
) {
    val maxXPosition: Int by lazy { filledElements.maxXPosition }
    val maxYPosition: Int by lazy { filledElements.maxYPosition }

    fun replacedSafetyMark(mapper: (Position, MineMark) -> (MineMark)): FilledElements {
        return filledElements.replacedMarkElements { position, mineMark ->
            mineMark.mapIfSafety(position, mapper)
        }
    }

    private fun MineMark.mapIfSafety(
        position: Position,
        mapper: (Position, MineMark) -> MineMark,
    ): MineMark {
        return if (isSafety) {
            mapper(position, this)
        } else {
            this
        }
    }

    fun mineCounts(positions: Collection<Position>): Int {
        return filledElements.count { position, mineMark ->
            positions.contains(position) && mineMark.isMine
        }
    }
}
