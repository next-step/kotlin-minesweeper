package minesweeper.domain

data class Landmine(private val row: Int = 0, private val column: Int = 0) : Cell {
    override fun row(): Int = row

    override fun column(): Int = column

    override fun display(): String = LANDMINE_SYMBOL

    companion object {
        private const val LANDMINE_SYMBOL = "*"
    }
}
