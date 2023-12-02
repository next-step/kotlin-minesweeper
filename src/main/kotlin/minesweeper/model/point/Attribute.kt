package minesweeper.model.point

data class Attribute(
    private val tileType: TileType,
    private val vision: Vision,
) {
}
