package minesweeper.model

data class Cell(val x: Int, val y: Int) {
    var surroundingMineCount: Int = 0
        private set

    fun increaseCount() {
        surroundingMineCount++
    }
}

