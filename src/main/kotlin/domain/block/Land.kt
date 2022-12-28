package domain.block

class Land(
    val nearMineCount: Int,
    private var _isOpened: Boolean = false
) : Block() {

    val isOpened
        get() = _isOpened

    override fun availableOpen(): Boolean = !_isOpened
    override fun isMine() = false

    override fun openBlock() {
        this._isOpened = true
    }
}
