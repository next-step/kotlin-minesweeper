package domain

@JvmInline
value class Row(val value: Int) {
    operator fun times(column: Column): Int {
        return value.times(column.value)
    }

    operator fun plus(row: Row): Row {
        return Row(this.value.plus(row.value))
    }
}
