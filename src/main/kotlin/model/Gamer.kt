package model

class Gamer(val board: Board) {

    fun clickCoordinate(coordinates: Coordinates) =
        board.updateShowedArea(coordinates)

    fun getCurrentGameBoard(): List<List<MineType>> {
        val row = board.map.coordinates.groupBy { it.row }.size
        return board.map.mineTypes
            .chunked(row)
    }
}
