package minesweeper.domain

class ProtoCell(val bomb: Boolean = false) {
    var count: Int = 0
        private set
    lateinit var sideCells: List<ProtoCell>
    val cell: Cell
        get() = CellLegacy(bomb, count)

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
