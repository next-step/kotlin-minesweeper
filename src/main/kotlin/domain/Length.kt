package domain

@JvmInline
value class Length(val value: Int) {

    init {
        require(value >= 0) { "value must be over than 0." }
    }
}
