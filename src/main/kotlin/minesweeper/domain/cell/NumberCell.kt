package minesweeper.domain.cell

class NumberCell(
    private val aroundMineCount: Int,
    position: Position
) : Cell(position) {
    init {
        require(aroundMineCount in 0..8) { "폭탄의 갯수는 0..8 이여야 한다." }
    }

    override fun isMine() = false

    override fun toString() = "$aroundMineCount"
}
