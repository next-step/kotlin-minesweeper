package minesweeper.domain

class Mines(private val mines: List<Mine>) {

    fun hasDuplicate(): Boolean {
        val nonDuplicateMines = mines.toSet()
        if (nonDuplicateMines.size != this.mines.size) return true
        return false
    }

    fun duplicateRemoved() = mines.distinct()

    fun size() = mines.size

    fun setIntoMap(originalMap: List<List<String>>): List<List<String>> {
        val mineMap =
            originalMap.map { it.toMutableList() }.toMutableList()

        return mineMap.mapIndexed { nth, row ->
            setIntoEachRow(nth, row)
            row.toList()
        }
    }

    private fun setIntoEachRow(nthRow: Int, row: MutableList<String>) {
        mines.filter { it.isIn(nthRow) }
            .forEach { mine ->
                row[mine.getX()] = mine.symbol
            }
    }
}
