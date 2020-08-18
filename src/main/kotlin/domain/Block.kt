package domain

data class Block(
    val position: Position,
    val isMine: Boolean
) {
    constructor(xPosition: Int, yPosition: Int, isMine: Boolean) : this(Position.of(xPosition, yPosition), isMine)

    companion object {
        fun ofNormalsFrom(positions: List<Position>): List<Block> {
            return positions.map { Block(it, false) }
        }

        fun ofMinesFrom(positions: List<Position>): List<Block> {
            return positions.map { Block(it, true) }
        }
    }
}
