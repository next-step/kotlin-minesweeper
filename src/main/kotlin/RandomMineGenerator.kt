import java.util.*

class RandomMineGenerator(mineNumber: Int, dotNumber: Int) : MineGenerator(mineNumber, dotNumber) {

    override fun isMine(): Boolean = mineNumber == dotNumber ||
            RANDOM.nextInt(100) <= MINE_GENERATE_PERCENT

    companion object {
        private const val MINE_GENERATE_PERCENT = 20
        val RANDOM = Random()
    }
}
