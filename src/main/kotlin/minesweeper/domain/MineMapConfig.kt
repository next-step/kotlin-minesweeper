package minesweeper.domain

data class MineMapConfig(
    val width: Int,
    val height: Int,
    val mineCount: Int,
) {
    init {
        require(width > 1) { "width must be greater than 1" }
        require(height > 1) { "height must be greater than 1" }
        require(mineCount > 0) { "mineCount must be greater than zero" }
    }
}
