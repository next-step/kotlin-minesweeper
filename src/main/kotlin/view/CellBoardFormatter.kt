package view

import cell.Cell
import cell.CellBoard
import cell.Coordinate
import cell.MineCell

fun format(board: CellBoard): String {
    val cells = board.cells
    val height = cells.keys.maxOf { it.y.value } + 1
    val width = cells.keys.maxOf { it.x.value } + 1
    val boardArray = initializeBoardArray(height, width)

    populateBoardArray(boardArray, cells)

    return boardArrayToString(boardArray)
}

private fun initializeBoardArray(
    height: Int,
    width: Int,
): Array<CharArray> {
    return Array(height) { CharArray(width) { 'C' } }
}

private fun populateBoardArray(
    boardArray: Array<CharArray>,
    cells: Map<Coordinate, Cell>,
) {
    cells.forEach { (coordinate, cell) ->
        val x = coordinate.x.value
        val y = coordinate.y.value

        boardArray[y][x] =
            when (cell) {
                is MineCell -> 'X'
                else -> 'C'
            }
    }
}

private fun boardArrayToString(boardArray: Array<CharArray>): String {
    return boardArray.joinToString("\n") { it.joinToString(" ") }
}
