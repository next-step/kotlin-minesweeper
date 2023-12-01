package minesweeper.domain

data class GameBoard(private val _height: Int, private val _width: Int) {
    val height get() = _height
    val width get() = _width

    val board: Array<Array<Cell>>

    init {
        require(_height > 0 && _width > 0) {
            "자연수를 입력해주세요."
        }
        board = Array(_height) { Array(_width) { Cell() } }
    }
}
