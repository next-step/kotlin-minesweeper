package minesweeper.domain

class Location private constructor(private val y: Int, private val x: Int) {

    fun getConvertIndex(width: Int): Int {
        return y * width + x
    }

    companion object {
        fun of(location: String?): Location {
            require(location != null && location.isNotBlank()) { "빈 값을 입력하면 안됩니다." }
            val locationPart = location.split(",").map { it.trim() }
            require(locationPart.size == LOCATION_SIZE) { "숫자 두개를 콤마(,)로 구분하여 입력해주세요" }
            val locations = locationPart.map { it.toInt() }

            return Location(locations[0], locations[1])
        }
        private const val LOCATION_SIZE = 2
    }
}
