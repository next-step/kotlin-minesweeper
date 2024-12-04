package map

class Width(
    val size: Int,
) {
    init {
        require(size >= 0) { "너비는 음수일 수 없습니다." }
    }
}
