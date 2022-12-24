package minesweeper.model

class MineBoard(val map: MineMap, private val mines: Mines) {
    init {
        require(checkCount(map, mines)) { "맵 크기보다 많은 수의 지뢰를 배치할 수 없습니다." }
        require(checkBounds(map, mines)) { "맵 크기를 벗어나는 곳에 지뢰를 배치할 수 없습니다." }
    }

    private fun checkCount(map: MineMap, mines: Mines) = mines.size < map.rowSize * map.columnSize
    private fun checkBounds(map: MineMap, mines: Mines) = mines.all(map::checkBounds)

    fun checkMine(cell: Cell): Boolean {
        return mines.contains(cell)
    }
}
