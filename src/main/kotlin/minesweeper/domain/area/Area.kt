package minesweeper.domain.area

data class Area(val width: Width, val height: Height) {

    fun getArea(): Int {
        return width.value * height.value
    }

    fun getX(count: Int): Int {
        return count % width.value
    }

    fun getY(count: Int): Int {
        return count / height.value
    }
}
