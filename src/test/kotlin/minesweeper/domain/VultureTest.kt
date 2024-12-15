package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class VultureTest : BehaviorSpec({
    given("Vulture 는") {
        val sut = Vulture()

        `when`("Location을 받아") {
            val location = Location(row = 1, column = 1)
            val result = sut.plant(location)

            then("해당 Location 을 가진 Landmine을 반환한다") {
                result.shouldBeTypeOf<Landmine>()

                result.location() shouldBe location
            }
        }
    }
})
