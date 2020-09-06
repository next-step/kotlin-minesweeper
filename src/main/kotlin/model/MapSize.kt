package model

class MapSize(x: Size, y: Size) {
    val size = x.size * y.size
    val maxX = x.size
    val maxY = x.size
}