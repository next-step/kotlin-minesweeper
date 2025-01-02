package domain

@JvmInline
value class Col(private val value: Int) : Comparable<Col> {
    override fun compareTo(other: Col): Int {
        return this.value.compareTo(other.value)
    }

    operator fun plus(other: Col): Col {
        return Col(this.value + other.value)
    }
}
