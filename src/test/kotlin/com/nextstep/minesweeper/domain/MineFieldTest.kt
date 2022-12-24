package com.nextstep.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class MineFieldTest : FunSpec({
    context("MineField") {
        test("지뢰 이중 매설 불가") {
            val mineField = MineField(10, 10)
            mineField.dispense(5)

            val exception = shouldThrow<IllegalArgumentException> {
                mineField.dispense(5)
            }
            exception.message shouldBe "이미 매설 되었습니다"
        }

        test("지뢰 초과 매설 불가") {
            val mineField = MineField(10, 10)

            val exception = shouldThrow<IllegalArgumentException> {
                mineField.dispense(200)
            }
            exception.message shouldBe "지뢰 매설 수량 초과 하였습니다"
        }
    }
})
