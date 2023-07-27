package domain

class Land(val height: Int, val width: Int, val count: Int) {
    val mines = mutableListOf<Mine>()

    init {
        repeat(count) {
            var newMine = makeRandomPosition()
            while (mines.contains(newMine)) {
                newMine = makeRandomPosition()
            }
            mines.add(newMine)
        }
    }

    private fun makeRandomPosition(): Mine {
        val x = (1..width).random()
        val y = (1..height).random()
        return Mine(x, y)
    }
}
