package minesweeper.domain

class Tile {
    var nearMineCount = 0
    var isMine = false
        private set
    fun setMine() {
        isMine = true
    }

    fun increaseNearMineCount() {
        if(isMine) {
            return
        }
        nearMineCount = nearMineCount.inc()
    }

}