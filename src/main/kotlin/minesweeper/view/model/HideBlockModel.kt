package minesweeper.view.model

class HideBlockModel(
    private val pattern: String = "□"
) : BlockModel() {
    override fun toString(): String = pattern
}
