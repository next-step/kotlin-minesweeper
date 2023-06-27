package minesweeper_refactor.view.model

enum class BlockStateView(val exposureName: String) {
    HIDDEN(exposureName = "C"),
    MINE(exposureName = "*"),
    ZERO(exposureName = "0"),
    ONE(exposureName = "1"),
    TWO(exposureName = "2"),
    THREE(exposureName = "3"),
    FOUR(exposureName = "4"),
    FIVE(exposureName = "5"),
    SIX(exposureName = "6"),
    SEVEN(exposureName = "7"),
    EIGHT(exposureName = "8"),
}
