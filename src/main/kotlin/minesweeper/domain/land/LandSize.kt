package minesweeper.domain.land

data class LandSize(val height: Int, val width: Int) {
    fun size() = height * width
}
