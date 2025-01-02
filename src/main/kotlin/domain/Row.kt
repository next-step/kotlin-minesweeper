package domain

@JvmInline
value class Row(private val value: Int) : Comparable<Row> {
    override fun compareTo(other: Row): Int {
        return this.value.compareTo(other.value)
    }

    operator fun plus(other: Row): Row {
        return Row(this.value + other.value)
    }
}
