package minesweeper.domain.block

class Cell : Block() {
    override fun isMines(): Boolean = false
}
