package minesweeper.domain

class Tile {
    var nearMineCount = 0
    var isMine = false
        private set

    var isOpen = false
        get() {
            if (isMine) return true
            return field
        }
    fun setMine() {
        isMine = true
    }

    fun open() {
        if (!isMine) {
            isOpen = true
        }
    }

    fun increaseNearMineCount() {
        if (isMine) {
            return
        }
        nearMineCount = nearMineCount.inc()
    }
}
