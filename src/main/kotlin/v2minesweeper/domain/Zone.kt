package v2minesweeper.domain

sealed class Zone(
    var isHidden: Boolean
) {
    open fun open() {
        isHidden = false
    }
}

private const val INIT_HIDDEN = true

class MineZone(
    isHidden: Boolean = INIT_HIDDEN
) : Zone(
    isHidden = isHidden
) {
    override fun open() {
        super.open()
        throw IllegalArgumentException("지뢰를 open했습니다.")
    }
}

class SafeZone(
    isHidden: Boolean = INIT_HIDDEN
) : Zone(
    isHidden = isHidden
)
