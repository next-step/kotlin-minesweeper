package domain.block

interface Block {
    val position: Position

    fun isAt(position: Position): Boolean = this.position == position

    fun getSurroundingPositions(): List<Position> = position.surroundings()

    fun isMine(): Boolean

    fun isClosed(): Boolean

    fun getMinesCount(): Int
}
