package ramdom

class MineRandomLogic : RandomInterface {
    override fun createRandomNumList(count: Int, maxValue: Int): List<Int> {
        return (START_INDEX until maxValue).shuffled().subList(START_INDEX, count)
    }

    companion object {
        private const val START_INDEX = 0
    }
}
