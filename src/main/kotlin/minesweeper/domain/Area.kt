package minesweeper.domain

data class Area(val width: Width, val height: Height) {

    fun getArea(): Int {
        return width.value * height.value
    }
}
