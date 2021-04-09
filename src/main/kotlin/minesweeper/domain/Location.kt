package minesweeper.domain

class Location constructor(val row: Int, val column: Int) {

    fun getConvertIndex(width: Int): Int {
        return row * width + column
    }

    companion object {
        fun of(location: String?): Location {
            require(!location.isNullOrBlank()) { "빈 값을 입력하면 안됩니다." }
            val locationPart = location.split(",").map { it.trim() }
            require(locationPart.size == LOCATION_SIZE) { "숫자 두개를 콤마(,)로 구분하여 입력해주세요" }
            val locations = locationPart.map { it.toInt() }

            return Location(locations[0], locations[1])
        }

        private const val LOCATION_SIZE = 2
    }
}
