class NumberOfMine(mineCount: Int, boardSize: BoardSize) {

    init {
        require(mineCount in MIN_MINE_NUMBER..boardSize.get()) {
            "지뢰 개수는 전체 크기를 초과할 수 없습니다."
        }
    }

    companion object {
        private const val MIN_MINE_NUMBER = 1
    }
}
