package domain.square

import domain.position.Position

open class Square(
    val position: Position
) {
    open val isMine: Boolean = false
}
