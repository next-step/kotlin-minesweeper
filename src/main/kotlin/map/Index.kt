package map

data class Index(
    val value: Int,
) {
    init {
        require(value >= 0) { "인덱스는 음수일 수 없습니다." }
    }
}

fun Int.toIndex() = Index(this)
