package minesweeper.domain

private const val MINIMUM_POSITION_NUMBER = 1

@JvmInline
value class MineSweeperBoard(
    val zones: Map<Position, Zone>,
) {
    fun openAllZone(): Map<Position, Int> {
        return zones.keys.associateWith { countOfNearMine(it) }
    }

    private fun countOfNearMine(position: Position): Int {
        return MineSearchDirection.values()
            .filter { isSatisfiedNextPositionCondition(position, it) }
            .count { zones[getNextPosition(position, it)] is MineZone }
    }

    private fun isSatisfiedNextPositionCondition(position: Position, mineSearchDirection: MineSearchDirection): Boolean {
        return (position.x + mineSearchDirection.x >= MINIMUM_POSITION_NUMBER) && (position.y + mineSearchDirection.y >= MINIMUM_POSITION_NUMBER)
    }

    private fun getNextPosition(position: Position, mineSearchDirection: MineSearchDirection): Position {
        return Position(position.x + mineSearchDirection.x, position.y + mineSearchDirection.y)
    }
}
