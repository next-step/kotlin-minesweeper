package domain.square

import domain.position.Position

abstract class Square(
    val position: Position
) {
    var isOpen: Boolean = false
    abstract val isMine: Boolean
    abstract val mineCountAround: Int
}
