package minesweeper.domain

class Cells(
    private val cellMap: Map<Coordinate, Cell>,
) : Map<Coordinate, Cell> by cellMap {
    init {
        require(cellMap.isNotEmpty()) {
            "빈 Map 으로 생성할 수 없습니다."
        }
    }

    fun countNeighboringMines(coordinate: Coordinate): Int =
        coordinate
            .neighbors
            .count { cellMap[it] is MinedCell }

    fun open(coordinate: Coordinate): Cells {
        requireCellExists(coordinate)
        checkNotAlreadyOpen(coordinate)

        return if (cellMap[coordinate] is MinedCell) {
            Cells(cellMap.replacing(coordinate, DetonatedMineCell))
        } else {
            openAllNeighbors(coordinate)
        }
    }

    fun isAnyEmptyCellClosed(): Boolean = cellMap.any { it.value is ClosedEmptyCell }

    fun isAnyMineDetonated(): Boolean = cellMap.any { it.value is DetonatedMineCell }

    private fun openAllNeighbors(coordinate: Coordinate): Cells {
        check(cellMap[coordinate] is ClosedEmptyCell)

        val newCellMap = cellMap.toMutableMap()
        val queue = ArrayDeque<Coordinate>()

        newCellMap[coordinate] = OpenedEmptyCell
        addToQueueIfZeroNeighboringMines(coordinate, queue)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            current
                .neighbors
                .filter { it in newCellMap && newCellMap[it] is ClosedEmptyCell }
                .forEach {
                    newCellMap[it] = OpenedEmptyCell
                    addToQueueIfZeroNeighboringMines(it, queue)
                }
        }

        return Cells(newCellMap)
    }

    private fun addToQueueIfZeroNeighboringMines(
        coordinate: Coordinate,
        queue: ArrayDeque<Coordinate>,
    ) {
        if (isZeroNeighboringMines(coordinate)) {
            queue.add(coordinate)
        }
    }

    private fun isZeroNeighboringMines(coordinate: Coordinate) = countNeighboringMines(coordinate) == 0

    private fun checkNotAlreadyOpen(coordinate: Coordinate) {
        check(cellMap[coordinate] !is OpenedEmptyCell) {
            "이미 열린 칸입니다."
        }
    }

    private fun requireCellExists(coordinate: Coordinate) {
        require(coordinate in cellMap) {
            "좌표에 칸이 없습니다."
        }
    }
}

internal fun <K, V> Map<K, V>.replacing(
    key: K,
    value: V,
): Map<K, V> = toMutableMap().apply { set(key, value) }
