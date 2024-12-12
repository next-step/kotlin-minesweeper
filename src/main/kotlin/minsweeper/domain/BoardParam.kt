package minsweeper.domain

data class BoardParam(
    val height: Int,
    val width: Int,
    val mineCount: Int,
) {
    init {
        require(mineCount <= height * width) { TOO_MANY_MINE_COUNT_EXCEPTION }
    }
    
    companion object {
        private const val TOO_MANY_MINE_COUNT_EXCEPTION = "지뢰 갯수가 높이 x 넓이를 초과할 수 없습니다"
    }
}
