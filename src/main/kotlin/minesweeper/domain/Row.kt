package minesweeper.domain

class Row(
    width: Int
) {
    private val row: Array<Block> = Array<Block>(width) { Block.Covered() }

    fun getColumnBlock(column: Int): Block {
        return row[column]
    }

    private fun isDuplicateMine(column: Int): Boolean {
        return row[column].isMine()
    }

    fun deployMine(column: Int): Boolean {
        if (!isDuplicateMine(column)) {
            row[column] = Block.Mine()
            return true
        }
        return false
    }
}
