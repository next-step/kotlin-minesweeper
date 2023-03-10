package domains

import kotlin.random.Random

class MinePositionGenerator(
    private val gameSize: GameSize,
    private val mineCount: Int
) {
    fun generateMinePositions(): Positions {
        val minePositions: MutableList<Position> = mutableListOf()
        while (minePositions.size != mineCount) {
            val position = generatePosition()
            if (!minePositions.contains(position)) {
                minePositions.add(position)
            }
        }
        return Positions(minePositions.toList())
    }

    private fun generatePosition(): Position {
        val x = Random.nextInt(gameSize.width)
        val y = Random.nextInt(gameSize.height)
        return Position.fromApplication(x, y)
    }
}
