package domain

data class Row(
    val cells: List<Cell>
) {
    constructor(vararg values: Int) : this(values.map { Cell.init(it) })

    companion object {
        fun create(range: IntRange): Row {
            return Row(range.map { Cell.init(it) })
        }
    }
}
