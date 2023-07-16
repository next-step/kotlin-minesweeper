package minesweeper.domain

class MinePoint(x: Int, y: Int) : Point(x, y) {
    override val symbol = MINE_SYMBOL

    companion object {
        private const val MINE_SYMBOL = "*"
    }
}
