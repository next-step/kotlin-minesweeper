package domain

import java.util.Random

class RandomMineGenerator(mineNumber: Int, dotNumber: Int) : MineGenerator(mineNumber, dotNumber) {

    override fun isMine(): Boolean = mineNumber == dotNumber ||
        RANDOM.nextInt(MAX_PERCENT) <= MINE_GENERATE_PERCENT

    companion object {
        private const val MINE_GENERATE_PERCENT = 20
        private const val MAX_PERCENT = 100
        val RANDOM = Random()
    }
}
