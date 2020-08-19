import domain.field.MineFieldGenerator
import domain.RandomMinePositionsSelector
import domain.field.Rectangle
import view.getHeightOfField
import view.getMinesCount
import view.getWidthOfField
import view.printGameStart
import view.printMineField

fun main() {
    val rectangle = Rectangle(getWidthOfField(), getHeightOfField())
    val mineField = MineFieldGenerator(RandomMinePositionsSelector())
        .create(rectangle, getMinesCount())
    printGameStart()
    printMineField(mineField)
}
