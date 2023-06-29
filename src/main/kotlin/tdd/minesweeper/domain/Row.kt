package tdd.minesweeper.domain

@JvmInline
value class Row(private val columns: List<SymbolPoint>) : List<SymbolPoint> by columns
