package minesweeper.domain.block

data class Cell(private val position: Position) : Block(position) {
    override fun isMines(): Boolean = false
}
