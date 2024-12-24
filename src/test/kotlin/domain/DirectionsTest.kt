package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DirectionsTest : StringSpec({
    "일치하는 셀의 정확한 개수를 반환한다." {
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.MineCell,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.MineCell,
                        ),
                    ),
                ),
            )
        val result = Directions.countMatching(1, 1, cells) { it is Cell.MineCell }
        result shouldBe 2
    }

    "일치하는 셀이 없을 때 0을 반환한다." {
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                ),
            )
        val result = Directions.countMatching(1, 1, cells) { it is Cell.MineCell }
        result shouldBe 0
    }

    "그리드의 경계에서의 엣지 케이스를 처리한다." {
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.MineCell,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.MineCell,
                        ),
                    ),
                ),
            )
        val result = Directions.countMatching(0, 0, cells) { it is Cell.MineCell }
        result shouldBe 0
    }

    "그리드의 모서리에서의 엣지 케이스를 처리한다." {
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.MineCell,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.MineCell,
                        ),
                    ),
                ),
            )
        val result = Directions.countMatching(2, 2, cells) { it is Cell.MineCell }
        result shouldBe 0
    }
})
