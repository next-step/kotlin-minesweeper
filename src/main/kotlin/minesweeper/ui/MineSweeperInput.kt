package minesweeper.ui

interface MineSweeperInput {
    fun requestWidth(): Int
    fun requestHeight(): Int
    fun requestMineCapacity(): Int
    fun requestMarkingPoint(): List<Int>
}
