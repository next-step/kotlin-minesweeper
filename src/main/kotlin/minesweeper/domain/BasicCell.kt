package minesweeper.domain

data class BasicCell(private val row: Int, private val column: Int) : Cell {
    override fun row(): Int = row

    override fun column(): Int = column

    override fun display(): String = CLOSED_SYMBOL

    companion object {
        private const val CLOSED_SYMBOL = "■"
    }
}
