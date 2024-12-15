package domain

class RandomMinePlacer : MinePlacer {
    override fun addMine(cells: List<Cell>) {
        val cell = cells.filter { !it.hasMine }.random()
        cell.addMine()
    }
}
