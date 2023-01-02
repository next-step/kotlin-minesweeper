package domain

data class Board(
    private val rectangle: Rectangle,
    private val blocks: Map<Position, Block>
) {
    fun getBlocks(): Map<Position, Block> = blocks

    fun getWidth(): Int = rectangle.getWidth()

    fun getHeight(): Int = rectangle.getHeight()

    fun getBlockByPosition(position: Position): Block? = blocks[position]
}
