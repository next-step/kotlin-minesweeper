package domain.square.mine

import domain.position.Position
import domain.square.Square

class Mine(
    position: Position
) : Square(position) {
    override val isMine: Boolean
        get() = true
}
