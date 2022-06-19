package minesweeper.domain

data class BoardField(val coordinate: Coordinate, val isMine: Boolean) {

    companion object {
        fun nonMine(coordinate: Coordinate): BoardField {
            return BoardField(coordinate, false)
        }

        fun mine(coordinate: Coordinate): BoardField {
            return BoardField(coordinate, true)
        }
    }
}
