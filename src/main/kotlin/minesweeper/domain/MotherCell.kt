package minesweeper.domain

class MotherCell(val bomb: Boolean = false) {
    var count: Int = 0
    var sideCells = emptyList<MotherCell>()

    fun increaseCount() {
        sideCells
            .filter { it.bomb.not() }
            .forEach {
                it.increase()
            }
    }

    private fun increase() {
        require(!bomb)
        count++
    }
}
