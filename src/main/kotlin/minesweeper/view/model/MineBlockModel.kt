package minesweeper.view.model

class MineBlockModel(
    private val pattern: String = "♣"
) : BlockModel() {
    override fun toString(): String = pattern
}
