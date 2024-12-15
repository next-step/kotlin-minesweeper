package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class RandomMinePlacerTest : FunSpec({
    test("RandomMinePlacer 테스트") {
        // given
        val randomMinePlacer = RandomMinePlacer()
        val cells =
            listOf(
                Cell.create(1, 1),
                Cell.create(1, 2),
                Cell.create(1, 3),
                Cell.create(2, 1),
                Cell.create(2, 2),
                Cell.create(2, 3),
                Cell.create(3, 1),
                Cell.create(3, 2),
                Cell.create(3, 3),
            )

        // when
        randomMinePlacer.addMine(cells)

        // then
        cells.filter { it.hasMine }.size shouldBe 1
    }
})
