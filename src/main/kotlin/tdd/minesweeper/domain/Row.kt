package tdd.minesweeper.domain

@JvmInline
value class Row(private val columns: List<SymbolPoint>) : List<SymbolPoint> by columns {
    fun <T> convert(mapper: (SymbolPoint) -> T): List<T> = columns.map(mapper)
}
