import ramdom.RandomInterface

class FakeMineRandomLogic : RandomInterface {
    override fun createRandomNumList(count: Int, maxValue: Int): List<Int> {
        return List(count) { index -> index }
    }
}
