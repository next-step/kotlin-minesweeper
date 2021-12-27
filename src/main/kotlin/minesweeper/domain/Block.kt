package minesweeper.domain

class Block(private val aroundMineCount: Int) : Cell() {
    override fun toString(): String {
        return aroundMineCount.toString()
    }
}
