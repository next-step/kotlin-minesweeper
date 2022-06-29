package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineSweeper.sweep

class MineSweeperTest : StringSpec({

    "주변 셀에 지뢰가 없으면 0 으로 표시한다." {
        val mineMap = MineMap(2, 2, 0)
        val sweptMineMap = mineMap.map().sweep()

        sweptMineMap shouldBe mineMap {
            rows(NumberCell(0), NumberCell(0))
            rows(NumberCell(0), NumberCell(0))
        }
    }

    "기준 셀 오른쪽에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(NumberCell(), MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(1), MineCell)
        }
    }

    "기준 셀 왼쪽에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(MineCell, NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell, NumberCell(1))
        }
    }

    "기준 셀 아래에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(NumberCell())
            rows(MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(1))
            rows(MineCell)
        }
    }

    "기준 셀 위에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(MineCell)
            rows(NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell)
            rows(NumberCell(1))
        }
    }

    "기준 셀 왼쪽 위에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(MineCell, NumberCell())
            rows(NumberCell(), NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell, NumberCell(1))
            rows(NumberCell(1), NumberCell(1))
        }
    }

    "기준 셀 오른 위에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(NumberCell(), MineCell)
            rows(NumberCell(), NumberCell())
        }.sweep() shouldBe mineMap {
            rows(NumberCell(1), MineCell)
            rows(NumberCell(1), NumberCell(1))
        }
    }

    "기준 셀 왼쪽 아래에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(NumberCell(), NumberCell())
            rows(MineCell, NumberCell())
        }.sweep() shouldBe mineMap {
            rows(NumberCell(1), NumberCell(1))
            rows(MineCell, NumberCell(1))
        }
    }

    "기준 셀 오른쪽 아래에 지뢰가 있으면 1로 표시된다." {
        mineMap {
            rows(NumberCell(), NumberCell())
            rows(NumberCell(), MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(1), NumberCell(1))
            rows(NumberCell(1), MineCell)
        }
    }

    "기준 셀 주변 지뢰 개수가 합산되어 표시된다. - 1" {
        mineMap {
            rows(NumberCell(), NumberCell())
            rows(MineCell, MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(2), NumberCell(2))
            rows(MineCell, MineCell)
        }

        mineMap {
            rows(MineCell, MineCell)
            rows(NumberCell(), NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell, MineCell)
            rows(NumberCell(2), NumberCell(2))
        }

        mineMap {
            rows(MineCell, NumberCell())
            rows(MineCell, NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell, NumberCell(2))
            rows(MineCell, NumberCell(2))
        }

        mineMap {
            rows(NumberCell(), MineCell)
            rows(NumberCell(), MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(2), MineCell)
            rows(NumberCell(2), MineCell)
        }
    }

    "기준 셀 주변 지뢰 개수가 모두 합산되어 표시된다. - 2" {
        mineMap {
            rows(NumberCell(), MineCell)
            rows(MineCell, MineCell)
        }.sweep() shouldBe mineMap {
            rows(NumberCell(3), MineCell)
            rows(MineCell, MineCell)
        }

        mineMap {
            rows(MineCell, NumberCell())
            rows(MineCell, MineCell)
        }.sweep() shouldBe mineMap {
            rows(MineCell, NumberCell(3))
            rows(MineCell, MineCell)
        }

        mineMap {
            rows(MineCell, MineCell)
            rows(NumberCell(), MineCell)
        }.sweep() shouldBe mineMap {
            rows(MineCell, MineCell)
            rows(NumberCell(3), MineCell)
        }

        mineMap {
            rows(MineCell, MineCell)
            rows(MineCell, NumberCell())
        }.sweep() shouldBe mineMap {
            rows(MineCell, MineCell)
            rows(MineCell, NumberCell(3))
        }
    }
})
