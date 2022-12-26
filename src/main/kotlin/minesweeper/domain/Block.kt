package minesweeper.domain

sealed interface Block {
    val blockText: String

    class LandMine(override val blockText: String = "*") : Block
    class Normal(override val blockText: String = "C") : Block
}
