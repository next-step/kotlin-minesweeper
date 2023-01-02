package minesweeper.domain.field

class Safe(private val aroundMineCount: Int) : Landed() {
    override fun aroundMineCount() = aroundMineCount
    override fun toString() = aroundMineCount.toString()
}
