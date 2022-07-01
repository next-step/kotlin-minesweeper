package minesweeper.domain

interface InputView {
    fun receiveBoardSize(): Area
    fun receiveMineCount(): MineCount
    fun receiveOpenCoordinate(): Coordinate
}
