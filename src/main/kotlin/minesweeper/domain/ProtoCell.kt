package minesweeper.domain

class ProtoCell(val bomb: Boolean = false) {
    var count: Int = 0
        private set
    lateinit var sideCells: List<ProtoCell>

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

    fun cellAndLink(): Pair<Cell, Links> {
        if (bomb) {
            return Cell(CellState.Bomb()) to Links()
        }
        if (count > 0) {
            return Cell(CellState.BombSide(count)) to Links()
        }
        val link = mutableListOf<Cell>()
        return Cell(CellState.Blank(link)) to Links(link)
    }

    private fun increase() {
        require(!bomb)
        count++
    }

    class Links(private val links: MutableList<Cell> = mutableListOf()) {
        fun save(cells: List<Cell>) {
            links.addAll(cells)
        }
    }
}
