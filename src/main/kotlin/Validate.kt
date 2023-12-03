object Validate {
    private const val ERR_MSG_MINE_OVERFLOW = "보드의 크기보다 지뢰가 더 많습니다."
    fun validateBoardInfo(width: Int, height: Int, mine: Int) {
        require(width * height >= mine) { ERR_MSG_MINE_OVERFLOW }
    }
}
