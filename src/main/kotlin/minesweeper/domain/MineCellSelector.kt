package minesweeper.domain

interface MineCellSelector {
    fun select(): Set<Point>
}

class RandomBasedMineCellSelector(val height: Int, val width: Int, val countOfMine: Int): MineCellSelector {
    override fun select(): Set<Point> {
        val pointOfMines = mutableSetOf<Point>()
        while (pointOfMines.size < countOfMine) {
            val randomY = (0 until height).random()
            val randomX = (0 until width).random()
            pointOfMines.add(Point(randomX, randomY))
        }
        return pointOfMines
    }
}
