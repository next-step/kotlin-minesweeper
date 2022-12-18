package minesweeper

class MineCell(
    private val hasMine: Boolean = false,
) {
    override fun toString(): String {
        return if (hasMine) "*" else "C"
    }
}
