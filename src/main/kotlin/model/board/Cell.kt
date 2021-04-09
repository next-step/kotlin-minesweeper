package model.board

data class Cell private constructor(val contents: Contents, val state: State) {

    companion object {
        private val CELLS = Contents.values().flatMap { contents -> State.values().map { state -> Cell(contents, state) } }

        val DEFAULT_CELL = get(Contents.ZERO, State.COVERED)

        val MINE_CELL = get(Contents.MINE, State.COVERED)

        fun get(contents: Contents, state: State): Cell {
            return CELLS.find { it == Cell(contents, state) }!!
        }
    }
}
