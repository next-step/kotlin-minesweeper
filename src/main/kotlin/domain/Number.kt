package domain

@JvmInline
value class Number(val value: String) {
    init {
        require(value.isNullOrBlank() && value.toInt() > 0) { "양수를 입력해주세요." }
        value.toInt()
    }
}
