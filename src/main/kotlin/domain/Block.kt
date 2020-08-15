package domain

data class Block(
    val position: Position,
    val isMine: Boolean
) {
    constructor(xPosition: Int, yPosition: Int, isMine: Boolean) : this(Position(xPosition, yPosition), isMine)
}
