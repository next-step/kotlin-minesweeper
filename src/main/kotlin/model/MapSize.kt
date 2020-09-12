package model

class MapSize(x: Size, y: Size) {
    val size = x.value * y.value
    val maxX = x.value
    val maxY = x.value
}
