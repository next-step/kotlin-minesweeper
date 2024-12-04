package mine

class MineCount(
    val count: Int,
) {
    init {
        require(count >= 0) { "지뢰의 개수는 음수일 수 없습니다." }
    }
}
