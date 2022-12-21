package minesweeper.model

class MineBoard(val map: MineMap, private val mines: Mines) {
    init {
        require(checkCount(map, mines))
        require(checkBounds(map, mines))
    }

    fun checkMine(cell: Cell): Boolean {
        return mines.contains(cell)
    }

    private fun checkCount(map: MineMap, mines: Mines) = mines.size < map.rowSize * map.columnSize
    private fun checkBounds(map: MineMap, mines: Mines) = mines.all(map::checkBounds)
}
