package map

class Height(
    val size: Int,
) {
    init {
        require(size >= 0) { "높이는 음수일 수 없습니다." }
    }
}
