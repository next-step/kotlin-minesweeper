package minesweeper.domain

class MinesweeperMapRow(private val mapRow: List<MapElement>) : Iterable<MapElement> by mapRow {
    fun open(colNumber: Int) {
        mapRow[colNumber].open()
    }

    fun isCovered(colNumber: Int): Boolean {
        return mapRow[colNumber].isCovered()
    }

    fun isMine(colNumber: Int): Boolean {
        return mapRow[colNumber] is MineMapElement
    }

    fun getNumber(colNumber: Int): Int {
        return (mapRow[colNumber] as NumberMapElement).value
    }

    fun getCoveredNumberMapElementCount(): Int {
        return count { element -> element.isCovered() && element is NumberMapElement }
    }

    companion object {
        private const val INVALID_WIDTH_ERROR_MESSAGE = "너비가 0이하일 수 없습니다"
        fun of(rowNumber: Int, width: Int, mineLocations: MineLocations): MinesweeperMapRow {
            require(width > 0) { INVALID_WIDTH_ERROR_MESSAGE }
            val mapRow = List(width) { colNumber -> mineLocations.getMapElement(Point(colNumber, rowNumber)) }
            return MinesweeperMapRow(mapRow)
        }
    }
}
