package minesweeper.domain.block

class Cell : Square() {
    override fun isMines(): Boolean = false
}
