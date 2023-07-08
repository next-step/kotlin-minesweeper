package domain

class MineSweeperGame(val mineSweeperMap: MineSweeperMap, private val mineCountNumber: MineCountNumber) {

    fun open(position: Position): Boolean {
        val cell = mineSweeperMap.getCellByPosition(position)
        return with(cell.property) {
            when {
                isCleanAroundMineCount() -> openCleanAroundPositions(cell)
                isMine() -> {
                    setOpen()
                    false
                }
                else -> {
                    setOpen()
                    true
                }
            }
        }
    }

    private fun openCleanAroundPositions(startCell: Cell): Boolean = with(mineSweeperMap) {
        val visited = mutableSetOf<Cell>()
        val queue = ArrayDeque<Cell>()
        queue.add(startCell)
        visited.add(startCell)

        while (queue.isNotEmpty()) {
            val visitedPositions = visited.map { it.position }
            val currentCell = queue.first()
            queue.removeFirst()

            val validPositions = currentCell.position.getValidAdjacentPositions(property.height, property.width)
            val notVisitedPositions = validPositions.filtered { !visitedPositions.contains(it) }
            val cells = getCellsByPositions(notVisitedPositions).filtered { !it.property.isMine() }
            val filteredCells = cells.getCleanAroundMineCount()

            visited.addAll(cells.value)
            queue.addAll(filteredCells.value)
        }

        visited.forEach { it.property.setOpen() }
        return true
    }

    fun isEnd(): Boolean {
        val existsOpenMine = mineSweeperMap.existsOpenMine()
        val isSameCountOfCloseAndMine = mineSweeperMap.getCloseCellCount() == mineCountNumber.value
        println("isSameCountOfCloseAndMine : $isSameCountOfCloseAndMine")
        return existsOpenMine || isSameCountOfCloseAndMine
    }

    fun getResult(): GameResult {
        val existsOpenMine = mineSweeperMap.existsOpenMine()
        val isSameCountOfCloseAndMine = mineSweeperMap.getCloseCellCount() == mineCountNumber.value
        return GameResult.valueOf(existsOpenMine, isSameCountOfCloseAndMine)
    }
}
