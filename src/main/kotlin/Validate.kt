object Validate {
    fun validateBoardInfo(width: Int, height: Int, mine: Int) {
        require(width * height >= mine) { "보드의 크기보다 지뢰가 더 많습니다." }
    }
}
