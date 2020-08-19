import domain.GameOverException
import domain.field.MineFieldGenerator
import domain.RandomMinePositionsSelector
import domain.block.Position
import domain.field.Rectangle
import view.getHeightOfField
import view.getMinesCount
import view.getWidthOfField
import view.getPositionToOpen
import view.printGameStart
import view.printMineField

fun main() {
    val rectangle = Rectangle(getWidthOfField(), getHeightOfField())
    var mineField = MineFieldGenerator(RandomMinePositionsSelector()).create(rectangle, getMinesCount())
    printGameStart()
    printMineField(mineField)
    while (true) {
        try {
            val inputPosition = Position.of(getPositionToOpen())
            mineField = mineField.open(inputPosition)
            printMineField(mineField)
        } catch (e: GameOverException) {
            println("Lose Game.")
        }
    }
}
