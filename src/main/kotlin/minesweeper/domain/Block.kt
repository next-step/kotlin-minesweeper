package minesweeper.domain

object Block : Cell() {
    private const val MARK = "C"
    override fun toString(): String {
        return MARK
    }
}
