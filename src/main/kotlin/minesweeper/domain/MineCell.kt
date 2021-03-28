package minesweeper.domain

internal class MineCell : Cell() {
    override val hasMine = true
    override val display: String = "*"
    override fun expose(cells: List<Cell>): Cell {
        return MineCell()
    }
}
