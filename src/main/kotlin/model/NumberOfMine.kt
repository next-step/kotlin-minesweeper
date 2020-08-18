package model

class NumberOfMine(private val number: Int, private val boardSize: BoardSize) {

    init {
        require(number in MIN_MINE_NUMBER..boardSize.get()) {
            "지뢰 개수는 ${MIN_MINE_NUMBER}보다 작거나 ${boardSize.get()}보다 클 수 없습니다."
        }
    }

    fun getMineIndexes() = (MIN_MINE_NUMBER..boardSize.get()).shuffled().take(number)

    companion object {
        private const val MIN_MINE_NUMBER = 1
    }
}
