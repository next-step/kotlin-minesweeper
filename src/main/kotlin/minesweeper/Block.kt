package minesweeper

sealed class Block {
    abstract val blockIndex: Int
    abstract val blockText: String

    class LandMine(override val blockIndex: Int, override val blockText: String = "*") : Block()
    class Normal(override val blockIndex: Int, override val blockText: String = "C") : Block()
}
