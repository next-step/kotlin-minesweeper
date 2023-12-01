package business

enum class CardVisibilityState {
    VISIBLE, HIDDEN, ;

    fun isVisible(): Boolean = this == VISIBLE
}
