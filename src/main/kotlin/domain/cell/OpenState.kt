package domain.cell

enum class OpenState {

    OPEN,
    HIDE,
    ;

    fun isHide(): Boolean {
        return this == HIDE
    }
}
