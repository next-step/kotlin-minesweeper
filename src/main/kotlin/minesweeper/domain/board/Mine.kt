package minesweeper.domain.board

class Mine private constructor(val isActive: Boolean) {
    companion object {
        val ACTIVE = Mine(true)
        val INACTIVE = Mine(false)
    }
}
