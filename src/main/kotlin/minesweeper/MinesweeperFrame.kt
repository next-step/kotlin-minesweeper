package minesweeper

class MinesweeperFrame(height: Int, width: Int, mineCount: Int) {

    private val _framePanel: Array<Array<MinesweeperCell>> = Array(height) { Array(width) { MinesweeperCell() } }
    private var _cells: MutableList<MinesweeperCell> = mutableListOf()

    val minePanel: Array<Array<MinesweeperCell>>
        get() = _framePanel.clone()

    init {
        _framePanel.forEach { it.forEach { cell -> _cells.add(cell) } }
        _cells.shuffle()
        (1..mineCount).forEach { _cells[it].setMine() }
    }
}
