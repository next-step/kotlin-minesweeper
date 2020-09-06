package model

import java.lang.Exception
import kotlin.collections.Map

class Map(val items: Map<Position, CellType>) {
    fun getPositionValue(position: Position): CellType {
        return items[position] ?: throw Exception("해당 좌표에 값이 없습니다.")
    }
}
