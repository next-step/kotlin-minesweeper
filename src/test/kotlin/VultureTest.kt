import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class VultureTest : BehaviorSpec({
    given("Vulture 는") {
        val sut = Vulture()

        `when`("BasicCell을 받아") {
            val basicCell = BasicCell(row = 1, column = 1)
            val result = sut.plantMine(basicCell)

            then("같은 좌표의 Landmine 을 반환한다") {
                result.shouldBeTypeOf<Landmine>()

                result.row() shouldBe basicCell.row()
                result.column() shouldBe basicCell.column()
            }
        }
    }
})
