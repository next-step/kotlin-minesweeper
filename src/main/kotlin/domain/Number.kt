package domain

@JvmInline
value class Number(val value: Int) {
    operator fun plus(row: Number): Number {
        return Number(this.value.plus(row.value))
    }
}
