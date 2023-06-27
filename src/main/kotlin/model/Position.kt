package model

data class Position(val x: Int, val y: Int) {

    init {
        require(x >= 0) { "x position must be positive. but provided `$x`" }
        require(y >= 0) { "y position must be positive. but provided `$y`" }
    }
}
