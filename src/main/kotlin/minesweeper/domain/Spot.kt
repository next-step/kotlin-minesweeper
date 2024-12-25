package minesweeper.domain

sealed interface OpenResult {
    data object Success : OpenResult

    data object AlreadyOpened : OpenResult

    data object GameOver : OpenResult
}

sealed class Spot(val position: Position) {
    private var isOpened = false

    fun open(): OpenResult {
        if (isOpened) {
            return OpenResult.AlreadyOpened
        }
        isOpened = true
        return OpenResult.Success
    }

    fun isOpened(): Boolean {
        return isOpened
    }

    abstract fun isMine(): Boolean
}

class SafeSpot(position: Position) : Spot(position) {
    var nearbyMineCount: Int = 0
        private set

    override fun isMine(): Boolean {
        return false
    }

    fun calculateNearbyMineCount(minePositions: Set<Position>) {
        val nearbyPositions = position.nearbyPositions()
        this.nearbyMineCount = nearbyPositions.count { it in minePositions }
    }
}

class MineSpot(position: Position) : Spot(position) {
    override fun isMine(): Boolean {
        return true
    }
}
