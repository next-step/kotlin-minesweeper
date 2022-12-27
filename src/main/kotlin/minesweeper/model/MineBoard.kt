package minesweeper.model

class MineBoard(private val mineMap: MineMap, private val mines: Mines) {
    init {
        require(checkCount(mineMap, mines)) { "맵 크기보다 많은 수의 지뢰를 배치할 수 없습니다." }
        require(checkBounds(mineMap, mines)) { "맵 크기를 벗어나는 곳에 지뢰를 배치할 수 없습니다." }
    }

    private fun checkCount(map: MineMap, mines: Mines) = mines.size < map.rowSize * map.columnSize
    private fun checkBounds(map: MineMap, mines: Mines) = mines.all(map::checkBounds)

    fun checkMine(cell: Cell): Boolean {
        return mines.contains(cell)
    }

    fun countNearMines(cell: Cell): Int {
        return cell.getNearCells().count(mines::contains)
    }

    fun findAllNearMineNumbers(): Map<Cell, Int> {
        return mineMap.selectAllCells()
            .filter { !checkMine(it) }
            .associateWith { countNearMines(it) }
    }
}
