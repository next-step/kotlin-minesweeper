package minesweeper.domain

data class Coordinate(val row: Int, val column: Int) {

    init {
        require(row >= 1 && column >= 1) { "블럭 좌표는 (1, 1) 부터 시작합니다. ($row, $column)" }
    }
}
