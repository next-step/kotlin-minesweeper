package minesweeper.view.model

class CoordinateView(inputCoordinate: String) {

    val x: Int
    val y: Int

    init {
        val (x, y) = inputCoordinate.split(DELIMITER, limit = INPUT_LIMIT)
            .map {
                it.trim()
                    .toInt()
            }

        this.x = x
        this.y = y
    }

    companion object {
        private const val DELIMITER: Char = ','
        private const val INPUT_LIMIT: Int = 2
    }
}
