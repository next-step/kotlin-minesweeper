package minesweeper.model.board

enum class BoardState {
    RUNNING,
    COMPLETED,
    MINE_EXPLODED;

    val isFinished: Boolean
        get() = this == COMPLETED || this == MINE_EXPLODED
}
