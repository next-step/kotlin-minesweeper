package minesweeper.domain.position

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize

class DirectionTest : FunSpec({
    test("8가지 Direction을 제공한다.") {
        Direction.values() shouldHaveSize 8
        Direction.values().map { it.row to it.col }.toSet() shouldHaveSize 8
    }
})
