package domain

@JvmInline
value class CellState private constructor(private val state: Pair<OpenState, MineState>) {
    constructor() : this(OpenState.CLOSED to MineState.NOT_MINE)

    fun isOpen(): Boolean = state.first == OpenState.OPEN
    fun isMine(): Boolean = state.second == MineState.MINE
    fun open(): CellState = CellState(OpenState.OPEN to state.second)
    fun layMine(): CellState = CellState(state.first to MineState.MINE)
    private enum class OpenState { OPEN, CLOSED }
    private enum class MineState { MINE, NOT_MINE }
}
