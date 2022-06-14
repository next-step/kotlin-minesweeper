import domain.RandomMineGenerator

fun main() {
    val randomMineGenerator = RandomMineGenerator()
    val mineController = MineController(randomMineGenerator)

    mineController.run()
}
