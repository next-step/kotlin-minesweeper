package minesweeper.domain.board

data class BoardSettings(val width: Int, val height: Int, val mineCounts: Int) {

    init {
        verify()
    }

    private fun verify() {
        require(width * height >= mineCounts) { "주어진 너비와 높이보다 많은 개수의 지뢰를 입력받을 수 없습니다." }
    }
}
