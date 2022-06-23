package domain

class Minesweeper(rows: List<Row>) : List<Row> by rows {
    constructor(vararg row: Row) : this(row.toList())
}
