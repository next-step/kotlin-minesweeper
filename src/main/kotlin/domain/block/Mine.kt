package domain.block

class Mine : Block() {
    override fun isMine() = true
    override fun surroundingMineCount() = throw UnsupportedOperationException("지뢰는 주변지뢰의 개수를 구할 수 없습니다.")
}
