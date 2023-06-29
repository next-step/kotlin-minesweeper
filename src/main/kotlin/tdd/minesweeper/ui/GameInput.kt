package tdd.minesweeper.ui

interface GameInput {
    fun requestHeight(): Int
    fun requestWidth(): Int
    fun requestMineCapacity(): Int
    fun requestMarkingPoint(): Pair<Int, Int>
}
