package mineswipper.domain.map

import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Row
import mineswipper.domain.map.position.Size

class Field(
    val field: Map<Row, Pedals>
) {
    fun findPedal(position: Position): Pedal {
        val pedals = field[position.toRow()]
        require(pedals != null) { VALID_MESSAGE }

        return pedals.get(position.x)
    }

    fun getSize(): Size {
        val pedals = field[Row(FIRST_ROW_FOR_SIZE)]
        require(pedals != null) { VALID_MESSAGE }

        return Size(field.size, pedals.value.size)
    }

    companion object {
        private const val VALID_MESSAGE: String = "해당 위치에 값이 존재하지 않습니다."
        private const val FIRST_ROW_FOR_SIZE: Int = 0
    }
}
