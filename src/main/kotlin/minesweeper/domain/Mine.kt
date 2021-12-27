package minesweeper.domain

class Mine() : Cell() {
    override fun toString(): String {
        return MARK
    }

    companion object {
        private const val MARK = "*"
    }
}
