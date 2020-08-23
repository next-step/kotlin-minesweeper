package model

class Gamer(val board: Board) {

    fun clickCoordinate(coordinates: Coordinates) =
        board.updateShowedArea(coordinates)

    fun getCurrentGameBoard(): List<List<MineType>> {
        val row = board.value.keys.groupBy { it.row }.size
        return board.value.values
            .chunked(row)
    }
}
