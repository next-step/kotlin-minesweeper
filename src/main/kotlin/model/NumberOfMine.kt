package model

class NumberOfMine(private val number: Int, private val boardSize: BoardSize) {

    init {
        require(number in MIN_MINE_NUMBER..boardSize.get()) {
            "지뢰 개수는 전체 크기를 초과할 수 없습니다."
        }
    }

    fun getMineIndexes() = (MIN_MINE_NUMBER..boardSize.get()).shuffled().take(number)

    companion object {
        private const val MIN_MINE_NUMBER = 1
    }
}
