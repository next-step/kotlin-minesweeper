package minesweeper.domain.block

data class Cell( val position: Position) : Block(position) {
    override fun isMines(): Boolean = false
}
