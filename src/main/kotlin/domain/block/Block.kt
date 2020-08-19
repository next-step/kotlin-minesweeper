package domain.block

interface Block {
    val position: Position

    fun isAt(position: Position): Boolean {
        return this.position == position
    }

    fun isMine(): Boolean

    fun getMinesCount(): Int
}
