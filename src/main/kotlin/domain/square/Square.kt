package domain.square

import domain.position.Position

abstract class Square(
    val position: Position
) {
    var isOpen: Boolean = false
        protected set
    abstract val isMine: Boolean
    abstract val mineCountAround: Int

    abstract fun open()
}
