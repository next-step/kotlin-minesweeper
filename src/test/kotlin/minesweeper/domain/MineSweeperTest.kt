package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class MineSweeperTest : StringSpec({

    "주변 셀에 지뢰가 없으면 0 으로 표시한다." {
        val mineMap = MineMap(2, 2, 0)
        val sweptMineMap = MineSweeper.sweep(mineMap.map())

        sweptMineMap.flatten().count { it is MineCell } shouldBe 0
        sweptMineMap.flatten().all { it.text() == "0" } shouldBe true
    }

    "기준 셀 오른쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), MineCell)
            )
        )

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].text() shouldBe "1"
    }

    "기준 셀 왼쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(
            listOf(
                listOf(MineCell, NumberCell())
            )
        )

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][1].text() shouldBe "1"
    }

    "기준 셀 아래에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(
            listOf(
                listOf(NumberCell()),
                listOf(MineCell)
            )
        )

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].text() shouldBe "1"
    }

    "기준 셀 위에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(
            listOf(
                listOf(MineCell),
                listOf(NumberCell())
            )
        )

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[1][0].text() shouldBe "1"
    }

    "기준 셀 왼쪽 위에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(MineCell, NumberCell()),
                listOf(NumberCell(), NumberCell())
            )
        )
        topLeft[0][0].shouldBeInstanceOf<MineCell>()
        topLeft[1][1].text() shouldBe "1"
    }

    "기준 셀 오른 위에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), MineCell),
                listOf(NumberCell(), NumberCell())
            )
        )
        topLeft[0][1].shouldBeInstanceOf<MineCell>()
        topLeft[1][0].text() shouldBe "1"
    }

    "기준 셀 왼쪽 아래에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), NumberCell()),
                listOf(MineCell, NumberCell())
            )
        )
        topLeft[1][0].shouldBeInstanceOf<MineCell>()
        topLeft[0][1].text() shouldBe "1"
    }

    "기준 셀 오른쪽 아래에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), NumberCell()),
                listOf(NumberCell(), MineCell)
            )
        )
        topLeft[1][1].shouldBeInstanceOf<MineCell>()
        topLeft[0][0].text() shouldBe "1"
    }

    "기준 셀 주변 지뢰 개수가 합산되어 표시된다. - 1" {
        MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), NumberCell()),
                listOf(MineCell, MineCell)
            )
        )[0][0].text() shouldBe "2"

        MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), MineCell),
                listOf(NumberCell(), MineCell)
            )
        )[0][0].text() shouldBe "2"
    }

    "기준 셀 주변 지뢰 개수가 모두 합산되어 표시된다. - 2" {
        MineSweeper.sweep(
            listOf(
                listOf(NumberCell(), MineCell),
                listOf(MineCell, MineCell)
            )
        )[0][0].text() shouldBe "3"

        MineSweeper.sweep(
            listOf(
                listOf(MineCell, NumberCell()),
                listOf(MineCell, MineCell)
            )
        )[0][1].text() shouldBe "3"

        MineSweeper.sweep(
            listOf(
                listOf(MineCell, MineCell),
                listOf(NumberCell(), MineCell)
            )
        )[1][0].text() shouldBe "3"

        MineSweeper.sweep(
            listOf(
                listOf(MineCell, MineCell),
                listOf(MineCell, NumberCell())
            )
        )[1][1].text() shouldBe "3"
    }
})
