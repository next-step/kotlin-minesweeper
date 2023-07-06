package domain

class MineMap(
    val height: Int = 0,
    val width: Int = 0,
    val numOfMine: Int = 0
) {
    init {
        require(numOfMine <= height * width)
    }
}
