package util

import domain.Position

object OpenPositionParser {

    private const val SEPARATOR = ","

    fun parse(inputPosition: String): Position {
        val location = inputPosition.split(SEPARATOR)
        require(location.size == 2) { "x, y 의 좌표값이 존재해야 합니다." }
        val x = location[0].toInt()
        val y = location[1].toInt()

        return Position(x, y)
    }
}
