package game.minesweeper.domain.map

data class MapConfig(val height: Int, val width: Int) {
    init {
        require(height > 0) { "높이는 0 보다 커야 합니다. (height: $height)" }
        require(width > 0) { "너비는 0 보다 커야 합니다. (width: $width)" }
    }

    fun configurableMine(mineCount: Int) = height * width >= mineCount
}
