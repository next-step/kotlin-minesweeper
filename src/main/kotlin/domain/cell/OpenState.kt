package domain.cell

enum class OpenState {
    OPEN,
    HIDE,
    ;

    fun isOpen(): Boolean {
        return this == OPEN
    }

    fun isHide(): Boolean {
        return this == HIDE
    }
}
