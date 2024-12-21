package cell

@JvmInline
value class Length(val value: Int) {
    init {
        require(value > 0) { "값은 0 이상이어야 합니다." }
    }
}
