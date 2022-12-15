package minesweeper

sealed class Block {
    abstract val blockText: String

    class LandMine(override val blockText: String = "*") : Block()
    class Normal(override val blockText: String = "C") : Block()
}
