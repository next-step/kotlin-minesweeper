package minsweeper.domain

class Board(val cells: Map<Coordinate, Cell>) {

    fun open(coordinate: Coordinate): OpenResult {
        val cell = cells[coordinate] ?: return OpenResult.INVALID_COORDINATE
        if (cell is Cell.Mine) {
            return OpenResult.MINE_FOUND
        }

        cell.open()

        return OpenResult.SUCCESS
    }

}