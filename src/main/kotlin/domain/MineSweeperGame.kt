package domain

class MineSweeperGame(val mines: MineField, private var leftMines: Int) {

    companion object {
        fun newGame(size: FieldSize, numberOfMines: Int): MineSweeperGame {
            val randomIndexs = createRandomIndexsForMine(size, numberOfMines)
            val mineField = MineField.createByIndexs(randomIndexs, size)
            return MineSweeperGame(mineField, numberOfMines)
        }

        private fun createRandomIndexsForMine(size: FieldSize, numberOfMines: Int) =
            with(size) {
                (0 until height * width)
                    .shuffled()
                    .take(numberOfMines)
            }
    }
}
