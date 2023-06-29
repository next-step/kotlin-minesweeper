package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import minesweeper.util.BinaryMineLocationGenerator

class MinesweeperMapTest : BehaviorSpec({
    val opened0Element = NumberMapElement(0)
    opened0Element.open()
    val opened1Element = NumberMapElement(1)
    opened1Element.open()
    val opened2Element = NumberMapElement(2)
    opened2Element.open()

    Given("지뢰찾기 전체 맵의 높이가 0이하로 주어졌다") {
        val height = 0
        val width = 10
        val mineCount = 0
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("지뢰찾기 전체 맵의 너비가 0이하로 주어졌다") {
        val height = 10
        val width = 0
        val mineCount = 0
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("지뢰찾기 전체 맵의 크기보다 더 많은 지뢰의 개수가 주어졌다") {
        val height = 10
        val width = 10
        val mineCount = height * width + 1
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("올바른 지뢰찾기 정보가 주어졌다") {
        val height = 10
        val width = 10
        val mineCount = height * width / 2
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("정상적으로 생성된다") {
                val expectedTopBottomRow = MinesweeperMapRow(
                    listOf(
                        MineMapElement(),
                        NumberMapElement(4),
                        MineMapElement(),
                        NumberMapElement(4),
                        MineMapElement(),
                        NumberMapElement(4),
                        MineMapElement(),
                        NumberMapElement(4),
                        MineMapElement(),
                        NumberMapElement(2),
                    ),
                )
                val expectedMiddleRow = MinesweeperMapRow(
                    listOf(
                        MineMapElement(),
                        NumberMapElement(6),
                        MineMapElement(),
                        NumberMapElement(6),
                        MineMapElement(),
                        NumberMapElement(6),
                        MineMapElement(),
                        NumberMapElement(6),
                        MineMapElement(),
                        NumberMapElement(3),
                    ),
                )
                val expectedRowList = listOf(
                    expectedTopBottomRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedTopBottomRow,
                )
                val expectedMap = MinesweeperMap(height, width, expectedRowList)
                MinesweeperMap.of(height, width, mineCount, BinaryMineLocationGenerator) shouldBe expectedMap
            }
        }
    }

    Given("올바른 지도가 주어졌다") {
        val height = 5
        val width = 5
        // 3 * 2 0 0
        // * * 2 0 0
        // 2 2 1 0 0
        // 0 0 0 0 0
        // 0 0 0 0 0
        val minesweeperMapRowList = listOf(
            MinesweeperMapRow(
                listOf(
                    NumberMapElement(3),
                    MineMapElement(),
                    NumberMapElement(2),
                    NumberMapElement(0),
                    NumberMapElement(0),
                ),
            ),
            MinesweeperMapRow(
                listOf(
                    MineMapElement(),
                    MineMapElement(),
                    NumberMapElement(2),
                    NumberMapElement(0),
                    NumberMapElement(0),
                ),
            ),
            MinesweeperMapRow(
                listOf(
                    NumberMapElement(2),
                    NumberMapElement(2),
                    NumberMapElement(1),
                    NumberMapElement(0),
                    NumberMapElement(0),
                ),
            ),
            MinesweeperMapRow(
                listOf(
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                ),
            ),
            MinesweeperMapRow(
                listOf(
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                    NumberMapElement(0),
                ),
            ),
        )
        val minesweeperMap = MinesweeperMap(height, width, minesweeperMapRowList)

        forAll(
            table(
                headers("x", "y"),
                row(-1, 0),
                row(0, -1),
                row(0, height + 1),
                row(width + 1, 0),
            ),
        ) { x, y ->
            When("지도의 범위를 벗어나는 좌표($x, $y)를 열어보면") {
                Then("예외가 던져진다") {
                    shouldThrow<IllegalArgumentException> { minesweeperMap.open(Point(x, y)) }
                }
            }
        }

        When("지뢰가 아닌 좌표를 열어보면") {
            Then("인접한 칸이 열리고 남은 지뢰가 아닌칸 개수가 반환된다") {

                val expectedRowList = listOf(
                    MinesweeperMapRow(
                        listOf(
                            NumberMapElement(3),
                            MineMapElement(),
                            opened2Element,
                            opened0Element,
                            opened0Element,
                        ),
                    ),
                    MinesweeperMapRow(
                        listOf(
                            MineMapElement(),
                            MineMapElement(),
                            opened2Element,
                            opened0Element,
                            opened0Element,
                        ),
                    ),
                    MinesweeperMapRow(
                        listOf(
                            opened2Element,
                            opened2Element,
                            opened1Element,
                            opened0Element,
                            opened0Element,
                        ),
                    ),
                    MinesweeperMapRow(
                        listOf(
                            opened0Element,
                            opened0Element,
                            opened0Element,
                            opened0Element,
                            opened0Element,
                        ),
                    ),
                    MinesweeperMapRow(
                        listOf(
                            opened0Element,
                            opened0Element,
                            opened0Element,
                            opened0Element,
                            opened0Element,
                        ),
                    ),
                )
                val expectedMap = MinesweeperMap(height, width, expectedRowList)
                val a =
                    minesweeperMap.open(Point(4, 4)).getOrThrow()
                a shouldBe 1
                minesweeperMap shouldBe expectedMap
            }
        }

        When("지뢰인 좌표를 열어보면") {
            Then("Failure가 반환된다") {
                minesweeperMap.open(Point(0, 1)).isFailure shouldBe true
            }
        }
    }
})
