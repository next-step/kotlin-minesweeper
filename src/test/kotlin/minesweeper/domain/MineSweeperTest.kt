package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineSweeperTest : StringSpec({

    "주변 셀에 지뢰가 없으면 0 으로 표시한다." {
        val mineMap = MineMap(2, 2, 0)
        val sweptMineMap = MineSweeper.sweep(mineMap.map())

        sweptMineMap.flatten().count { it is MineCell } shouldBe 0
        sweptMineMap.flatten().all { it.mineCountAround == 0 } shouldBe true
    }

    "기준 셀 오른쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(listOf(listOf(Cell(), MineCell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].mineCountAround shouldBe 1
    }

    "기준 셀 왼쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(listOf(listOf(MineCell(), Cell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][1].mineCountAround shouldBe 1
    }

    "기준 셀 아래에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(listOf(listOf(Cell()), listOf(MineCell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].mineCountAround shouldBe 1
    }

    "기준 셀 위에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweep(listOf(listOf(MineCell()), listOf(Cell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[1][0].mineCountAround shouldBe 1
    }

    "기준 셀 왼쪽 위에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(MineCell(), Cell()),
                listOf(Cell(), Cell())
            )
        )
        topLeft[0][0].isMineCell shouldBe true
        topLeft[1][1].mineCountAround shouldBe 1
    }

    "기준 셀 오른 위에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(Cell(), MineCell()),
                listOf(Cell(), Cell())
            )
        )
        topLeft[0][1].isMineCell shouldBe true
        topLeft[1][0].mineCountAround shouldBe 1
    }

    "기준 셀 왼쪽 아래에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(Cell(), Cell()),
                listOf(MineCell(), Cell())
            )
        )
        topLeft[1][0].isMineCell shouldBe true
        topLeft[0][1].mineCountAround shouldBe 1
    }

    "기준 셀 오른쪽 아래에 지뢰가 있으면 1로 표시된다." {
        val topLeft = MineSweeper.sweep(
            listOf(
                listOf(Cell(), Cell()),
                listOf(Cell(), MineCell())
            )
        )
        topLeft[1][1].isMineCell shouldBe true
        topLeft[0][0].mineCountAround shouldBe 1
    }

    "기준 셀 주변 지뢰 개수가 합산되어 표시된다. - 1" {
        MineSweeper.sweep(
            listOf(
                listOf(Cell(), Cell()),
                listOf(MineCell(), MineCell())
            )
        )[0][0].mineCountAround shouldBe 2

        MineSweeper.sweep(
            listOf(
                listOf(Cell(), MineCell()),
                listOf(Cell(), MineCell())
            )
        )[0][0].mineCountAround shouldBe 2
    }

    "기준 셀 주변 지뢰 개수가 모두 합산되어 표시된다. - 2" {
        MineSweeper.sweep(
            listOf(
                listOf(Cell(), MineCell()),
                listOf(MineCell(), MineCell())
            )
        )[0][0].mineCountAround shouldBe 3

        MineSweeper.sweep(
            listOf(
                listOf(MineCell(), Cell()),
                listOf(MineCell(), MineCell())
            )
        )[0][1].mineCountAround shouldBe 3

        MineSweeper.sweep(
            listOf(
                listOf(MineCell(), MineCell()),
                listOf(Cell(), MineCell())
            )
        )[1][0].mineCountAround shouldBe 3

        MineSweeper.sweep(
            listOf(
                listOf(MineCell(), MineCell()),
                listOf(MineCell(), Cell())
            )
        )[1][1].mineCountAround shouldBe 3
    }
})
