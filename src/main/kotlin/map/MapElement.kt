package map

import map.position.Position

class MapElement(
    val position: Position,
    private var type: MapElementType = MapElementType.EMPTY,
) {
    fun changeType(type: MapElementType) {
        this.type = type
    }

    fun getSymbol(): String {
        return type.symbol
    }

    fun isMine() = type == MapElementType.MINE

    override fun toString(): String {
        return getSymbol()
    }
}
