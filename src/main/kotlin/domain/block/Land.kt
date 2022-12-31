package domain.block

class Land(
    val nearMineCount: Int,
    private var _isOpened: Boolean = false
) : OpenAbleBlock {

    override fun isOpened() = _isOpened
    override fun isMine() = false

    override fun openBlock() {
        this._isOpened = true
    }
}
