package domain.square

import domain.position.Position
import domain.square.mine.Mines

class NormalSquare(
    position: Position,
    mines: Mines
) : Square(position) {
    override val isMine: Boolean
        get() = false
    override val mineCountAround: Int = position.aroundPosition().filter { mines.isMine(it) }.count()
}
