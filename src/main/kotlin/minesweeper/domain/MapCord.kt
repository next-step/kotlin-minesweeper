package minesweeper.domain

data class MapCord(
    val x: Int,
    val y: Int
) {
    init {
        require(x >= 0 && y >= 0) { "좌표에 음수가 있을 수 없습니다." }
    }

    operator fun plus(shiftCord: ShiftCord): MapCord? {
        if (x + shiftCord.x < 0 || y + shiftCord.y < 0) return null
        return MapCord(x + shiftCord.x, y + shiftCord.y)
    }

    operator fun minus(shiftCord: ShiftCord): MapCord? {
        if (x - shiftCord.x < 0 || y - shiftCord.y < 0) return null
        return MapCord(x - shiftCord.x, y - shiftCord.y)
    }
}

class MapCordBuilder(
    private val _x: Int = -1,
    private val _y: Int = -1
) {
    fun setX(input: Int): MapCordBuilder {
        return MapCordBuilder(input, _y)
    }

    fun setX(range: IntProgression): List<MapCordBuilder> {
        return range.map { newX -> MapCordBuilder(newX, _y) }
    }

    fun setY(newY: Int): MapCordBuilder {
        return MapCordBuilder(_x, newY)
    }

    fun setY(newYRange: IntProgression): List<MapCordBuilder> {
        return newYRange.map { newY -> MapCordBuilder(_x, newY) }
    }

    fun build(): MapCord = MapCord(_x, _y)
}
