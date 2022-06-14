package domain

import kotlin.random.Random

class RandomMineGenerator : MineGenerator {
    override fun generateMine(height: Int, width: Int): MinePosition {
        return MinePosition(Random.nextInt(height), Random.nextInt(width))
    }
}
