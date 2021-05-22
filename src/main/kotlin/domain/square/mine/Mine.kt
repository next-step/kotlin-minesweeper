package domain.square.mine

import domain.position.Position
import domain.square.Square

class Mine(
    position: Position
) : Square(position) {
    override val isMine: Boolean
        get() = true
    override val mineCountAround: Int
        get() = throw UnsupportedOperationException("지뢰 주변의 지뢰 개수는 구할 수 없습니다.")

    override fun open() {
        throw UnsupportedOperationException("지뢰는 open할 수 없습니다.")
    }
}
