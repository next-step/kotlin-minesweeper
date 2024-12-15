package domain

class MineSweeperMapBlock(
    private var _isMine: Boolean = false,
    private var _mineAroundCount: Int = 0,
    private var _isOpen: Boolean = false,
) {
    val isMine
        get() = _isMine
    val mineAroundCount
        get() = _mineAroundCount
    val isOpen: Boolean
        get() = _isOpen

    fun setMine() {
        _isMine = true
    }

    fun increaseMineAroundCount() {
        _mineAroundCount++
    }

    fun open() {
        _isOpen = true
    }
}
