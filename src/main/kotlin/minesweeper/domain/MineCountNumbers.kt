package minesweeper.domain

class MineCountNumbers(
    private val mineCountNumbers: List<MineCountNumber>
) {
    fun getMineCountNumber(position: Position): NearMineCount {
        this.mineCountNumbers.forEach {
            if (it.position == position) {
                return it.count
            }
        }
        throw IllegalArgumentException("해당하는 위치값이 없습니다.")
    }
}
