package domain.block

data class ClosedBlock(
    override val position: Position
) : Block {

    constructor(x: Int, y: Int) : this(Position.of(x, y))

    override fun isMine(): Boolean = false

    override fun isClosed(): Boolean = true

    override fun getMinesCount(): Int {
        throw UnsupportedOperationException("닫힌 블록은 주변 지뢰 개수를 가지지 않습니다.")
    }
}
