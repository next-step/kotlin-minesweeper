package domain

data class Board(
    private val rectangle: Rectangle,
    private val blocks: List<Block>
) {
    fun getBlocks(): List<Block> = blocks
    fun getWidth(): Int = rectangle.getWidth()
    fun getHeight(): Int = rectangle.getHeight()
}
