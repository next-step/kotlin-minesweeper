package domain

import domain.block.Block
import domain.block.Nothing

class MineBoard(val width: Int, private val height: Int, value: Map<Coordinate, Block>) {

    private val _value = value.toMutableMap()

    val value: Map<Coordinate, Block>
        get() = _value.toMap()

    init {
        _value.keys
            .filterNot { _value[it]!!.isMine() }
            .forEach { _value[it] = calculateSurroundingMineCount(it) }
    }

    private fun calculateSurroundingMineCount(coordinate: Coordinate): Block {
        val surroundingMineCount = coordinate.getSurroundingCoordinates(maxX = width, maxY = height)
            .count { value[it]?.isMine() ?: false }

        return Nothing(surroundingMineCount)
    }

    fun open(coordinate: Coordinate) {
        require(_value.contains(coordinate)) { "해당 좌표가 존재하지 않습니다. 좌표: $coordinate, width: $width, height: $height" }
        _value[coordinate] = _value[coordinate]!!.open()

        if (!isZero(coordinate)) {
            return
        }

        coordinate.getFourWayCoordinates(maxX = width, maxY = height)
            .filterNot { _value[it]!!.isOpened() }
            .forEach { open(it) }
    }

    private fun isZero(coordinate: Coordinate): Boolean {
        require(_value.contains(coordinate)) { "해당 좌표가 존재하지 않습니다. 좌표: $coordinate, width: $width, height: $height" }
        return _value[coordinate]!!.isZero()
    }

    fun notExistsToOpen() = _value.values.filter { !it.isMine() }.none { !it.isOpened() }

    fun existsOpenedMine() = _value.values.filter { it.isMine() }.any { it.isOpened() }
}
