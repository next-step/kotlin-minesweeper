package map

import map.position.Position

class Tile(
    val position: Position,
    private var type: TileType = TileType.EMPTY,
) {
    fun changeType(type: TileType) {
        this.type = type
    }

    fun getSymbol(): String {
        return type.symbol
    }

    fun isMine() = type == TileType.MINE

    override fun toString(): String {
        return getSymbol()
    }
}
