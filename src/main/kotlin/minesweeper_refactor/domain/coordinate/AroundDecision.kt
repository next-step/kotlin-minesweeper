package minesweeper_refactor.domain.coordinate

interface AroundDecision {

    fun decide(): List<Coordinate>

    companion object {
        internal const val MOVE_FORWARD: Int = 1
        internal const val MOVE_BACKWARD: Int = -1
        internal const val STOP: Int = 0
    }
}
