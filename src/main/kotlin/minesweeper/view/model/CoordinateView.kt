package minesweeper.view.model

class CoordinateView(inputCoordinate: String) {

    val x: Int
    val y: Int

    init {
        val (x, y) = inputCoordinate.split(DELIMITER, limit = INPUT_LIMIT)
            .map { it.toInt() }

        this.x = x
        this.y = y
    }

    companion object {
        private const val DELIMITER: String = ", "
        private const val INPUT_LIMIT: Int = 2
    }
}
