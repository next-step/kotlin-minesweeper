package minesweeper.domain

class Block(private val aroundMineCount: Int = 0) : Cell() {
    override fun toString(): String {
        return aroundMineCount.toString()
    }
}
