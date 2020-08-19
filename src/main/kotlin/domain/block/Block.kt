package domain.block

interface Block {
    val position: Position
    val isClose: Boolean

    fun isAt(position: Position): Boolean = this.position == position

    fun getSurroundingPositions(): List<Position> = position.surroundings()

    fun isMine(): Boolean

    fun getMinesCount(): Int

    fun open(): Block
}
