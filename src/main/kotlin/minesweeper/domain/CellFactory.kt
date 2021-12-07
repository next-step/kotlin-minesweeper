package minesweeper.domain

object CellFactory {

    fun getCell(isMine: Boolean, aroundMineCount: MineCount): Cell {
        return when {
            isMine -> MineCell
            else -> BlockCell(aroundMineCount)
        }
    }
}
