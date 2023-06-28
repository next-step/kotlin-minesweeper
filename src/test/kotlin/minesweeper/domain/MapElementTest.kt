package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table

class MapElementTest : BehaviorSpec({
    forAll(
        table(
            headers("value"),
            row(-1),
            row(9),
        ),
    ) { value ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("예외가 던져진다") {
                    shouldThrow<IllegalArgumentException> { NumberMapElement(value) }
                }
            }
        }
    }

    forAll(
        table(
            headers("value"),
            row(0),
            row(1),
            row(2),
            row(3),
            row(4),
            row(5),
            row(6),
            row(7),
            row(8),
        ),
    ) { value ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("예외가 던져지지 않는다") {
                    shouldNotThrowAny { NumberMapElement(value) }
                }
            }
        }
    }
})
