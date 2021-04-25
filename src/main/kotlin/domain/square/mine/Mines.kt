package domain.square.mine

import domain.position.Position

class Mines(val values: List<Mine>) {
    fun isMine(position: Position): Boolean {
        return values.any { it.position == position }
    }
}
