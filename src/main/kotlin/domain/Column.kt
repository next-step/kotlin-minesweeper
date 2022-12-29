package domain

@JvmInline
value class Column(val value: Int) {
    operator fun plus(column: Column): Column {
        return Column(this.value.plus(column.value))
    }
}
