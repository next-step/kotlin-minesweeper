package domain.block

interface Block {
    val position: Position
    val isClose: Boolean

    fun isAt(position: Position): Boolean {
        return this.position == position
    }

    fun isMine(): Boolean

    fun getMinesCount(): Int

    fun open(): Block
}
