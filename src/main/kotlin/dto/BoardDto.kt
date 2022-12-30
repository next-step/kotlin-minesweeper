package dto

import domain.Board
import domain.Coordinate
import domain.Land
import domain.Mine

data class BoardDto(
    private val board: Board
) {
    val rows: List<Row> = getBoardCondition()

    private fun getBoardCondition(): List<Row> {
        val rows = (0 until board.height.value).map { height ->
            Row((0 until board.width.value).map { width -> Col(getLandInfo(Coordinate(height, width))) })
        }
        return rows
    }

    private fun getLandInfo(coordinate: Coordinate): String {
        return when (val field = board.getField(coordinate)) {
            is Land -> getMineCntOrLandString(coordinate, field.isOpened)
            is Mine -> LAND_STRING
        }
    }

    private fun getMineCntOrLandString(coordinate: Coordinate, isOpened: Boolean): String {
        return when (isOpened) {
            true -> board.getNearByMineCount(coordinate).toString()
            false -> LAND_STRING
        }
    }

    companion object {
        private const val LAND_STRING = "C"
    }
}

data class Row(
    val cols: List<Col>
) : List<Col> by cols

data class Col(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}
