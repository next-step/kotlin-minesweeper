package model.board

data class Cell private constructor(val contents: Contents, val state: State) {
    val isMine: Boolean
        get() = contents == Contents.MINE

    fun asUncovered(mineCount: Int): Cell {
        return get(if (isMine) Contents.MINE else Contents.mineCountOf(mineCount)!!, State.UNCOVERED)
    }

    companion object {
        private val CELLS = Contents.values().flatMap { contents -> State.values().map { state -> Cell(contents, state) } }

        val DEFAULT_CELL = get(Contents.ZERO, State.COVERED)

        val MINE_CELL = get(Contents.MINE, State.COVERED)

        fun get(contents: Contents, state: State): Cell {
            return CELLS.find { it == Cell(contents, state) }!!
        }
    }
}
