package minesweeper.domain

class MotherCell(val bomb: Boolean = false) {
    var count: Int = 0
        private set
    lateinit var sideCells: List<MotherCell>
    val cell: Cell
        get() = Cell(bomb, count)

    fun increaseCount() {
        if (!bomb) {
            return
        }

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
