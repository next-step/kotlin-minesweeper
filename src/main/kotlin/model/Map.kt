package model

import kotlin.collections.Map

class Map(val items: Map<Position, Type>) {
    fun getPositionValue(position: Position): Type {
        return items[position] ?: throw Exception("해당 좌표에 값이 없습니다.")
    }
}
