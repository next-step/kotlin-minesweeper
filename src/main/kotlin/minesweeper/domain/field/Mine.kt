package minesweeper.domain.field

class Mine : Landed() {
    override fun aroundMineCount(): Int {
        throw IllegalStateException("해당 필드는 지뢰입니다.")
    }

    override fun toString() = MINE

    companion object {
        private const val MINE = "*"
    }
}
