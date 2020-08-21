import model.Mine
import model.MinePlate

class MinesweeperGame() {
    fun start(minePlate: MinePlate, mine: Mine): MinePlate {
        repeat(mine.value) {
            var colRandom: Int
            var rowRandom: Int
            do {
                colRandom = (minePlate.value.indices).random()
                rowRandom = (minePlate.value[0].indices).random()
            } while (minePlate.value[rowRandom][colRandom] == MINE_SYMBOL)
            minePlate.value[rowRandom][colRandom] = MINE_SYMBOL
        }
        return minePlate
    }

    companion object {
        const val MINE_SYMBOL = "*"
    }
}
