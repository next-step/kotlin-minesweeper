package minesweeper.domain.block

class Mines : Square() {
    override fun isMines(): Boolean = true
}
