package minesweeper

sealed class MapTile {
    object Mine : MapTile()
    data class Blank(val nearCount: Int) : MapTile() {
        operator fun plus(number: Int): Blank {
            return Blank(this.nearCount + number)
        }
    }
}
