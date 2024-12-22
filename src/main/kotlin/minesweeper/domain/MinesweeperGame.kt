package minesweeper.domain

class MinesweeperGame(val field: Field) {
    var isFinished = false

    fun openSpot(position: Position) {
        if (field.getSpot(position).isMine()) {
            isFinished = true
            return
        }
        field.openSpot(position)
    }
}
