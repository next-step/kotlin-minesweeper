package domain

class MineSweeperMapBlock(
    var _isMine: Boolean = false,
    var _mineAroundCount: Int = 0,
) {
    val isMine
        get() = _isMine
    val mineAroundCount
        get() = _mineAroundCount

    fun setMine(isMine: Boolean) {
        _isMine = isMine
    }

    fun increaseMineAroundCount() {
        _mineAroundCount++
    }
}
