package minesweeper.domain

data class BoardSize(val height: Int, val width: Int) {

    init {
        require(height > 0) { "[$height] must greater than 0" }
        require(width > 0) { "[$width] must greater than 0" }
    }

    val area = height * width
}
