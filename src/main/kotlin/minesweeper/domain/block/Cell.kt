package minesweeper.domain.block

data class Cell(override val position: Position) : Block(position) {
    override fun isMines(): Boolean = false
}
