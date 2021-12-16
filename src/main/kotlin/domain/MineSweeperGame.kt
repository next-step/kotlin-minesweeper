package domain

class MineSweeperGame(val mineField: MineField) {

    companion object {
        fun newGame(size: FieldSize, numberOfMines: Int): MineSweeperGame {
            val randomIndexs = createRandomIndexsForMine(size, numberOfMines)
                .map { Point(it % size.width, it / size.width) }.toSet()
            val mineField = MineField.createByIndexs(randomIndexs, size)
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
