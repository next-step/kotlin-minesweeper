package domain

data class Mine(
    override val position: Position
) : Block {

    constructor(x: Int, y: Int) : this(Position.of(x, y))

    override fun isMine(): Boolean = true

    override fun getMinesCount(): Int {
        throw UnsupportedOperationException("지뢰는 주변 지뢰의 개수를 가지고 있지 않습니다.")
    }

    companion object {
        fun from(positions: List<Position>): List<Block> {
            return positions.map { Mine(it) }
        }
    }
}
