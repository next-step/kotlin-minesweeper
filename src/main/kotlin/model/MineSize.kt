package model

class MineSize(val mineSize: Int) {
    init {
        require(mineSize > 0) { "지뢰 개수는 0보다 커야 합니다." }
    }
}
