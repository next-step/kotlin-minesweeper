package minesweeper.domain

object Mine : Cell() {
    private const val MARK = "*"
    override fun toString(): String {
        return MARK
    }
}
