package domain

class NeighborMineCount(val count: Int) {
    init {
        require(count >= 0) { "count 는 음수일 수 없습니다." }
    }
}
