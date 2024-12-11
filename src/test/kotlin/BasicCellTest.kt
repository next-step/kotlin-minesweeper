import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BasicCellTest : BehaviorSpec({
    given("BasicCell 은") {
        val sut = BasicCell()

        `when`("기본 상태의 display 값은") {
            val result = sut.display()

            then("■ 이다") {
                result shouldBe "■"
            }
        }
    }
})
