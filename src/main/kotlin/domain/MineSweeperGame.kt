package domain

class MineSweeperGame(private val mineField: MineField) {

    fun allSlots() = mineField.allSlots()

    fun checkAt(point: Point): GamesStates {
        if (checkIsMineAt(point))
            return GamesStates.LOSE
        if (isAllGroundChecked())
            return GamesStates.WIN
        return GamesStates.PLAYABLE
    }

    fun checkIsMineAt(point: Point): Boolean {
        if (mineField.isMine(point)) {
            mineField.changeChecked(point)
            return true
        }
        mineField.changeNearZeroSlots(point)
        return false
    }

    fun isAllGroundChecked(): Boolean {
        val leftNotCheckedGround = mineField.allSlots()
            .filter { !it.isMine() && !it.isChecked() }
            .size
        return leftNotCheckedGround == 0
    }

    companion object {
        fun newGame(size: FieldSize, numberOfMines: Int): MineSweeperGame {
            val randomIndexs = createRandomIndexsForMine(size, numberOfMines)
                .map { Point(it % size.width, it / size.width) }.toSet()
            val mineField = MineField.createByIndexs(randomIndexs, size)
            mineField.setNearMines()
            return MineSweeperGame(mineField)
        }

        private fun createRandomIndexsForMine(size: FieldSize, numberOfMines: Int) =
            with(size) {
                (0 until height * width)
                    .shuffled()
                    .take(numberOfMines)
            }
    }
}
