package minesweeper.domain.cell

class HiddenState {
    private var _isHidden: Boolean = true

    val isHidden: Boolean
        get() = _isHidden

    fun changeVisible() {
        _isHidden = false
    }
}
