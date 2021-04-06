package minesweeper

class MinesweeperCell {
    private var hasMine: Boolean = false

    val isMine: Boolean
        get() = hasMine

    fun setMine() {
        hasMine = true
    }
}
