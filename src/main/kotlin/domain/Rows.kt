package domain

import domain.block.Block
import domain.coord.Coordinate

class Rows(
    val items: List<Row>
) {

    fun blockOf(coordinate: Coordinate): Block {
        return items[coordinate.y.value].blockOf(coordinate.x)
    }

    fun allOpened(): Boolean {
        return items.all { it.isAllOpened() }
    }

    fun isRangeRowOf(value: Int): Boolean {
        return value < items.size
    }

    fun isRangeCellOf(value: Int): Boolean {
        require(items.isNotEmpty()) { "row가 비어있을 수는 없어요." }
        return items[0].isRangeLessThen(value)
    }
}
