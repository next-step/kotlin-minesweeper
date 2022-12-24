package com.nextstep.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class SizeTest : FunSpec({
    context("Size") {
        test("size 초기화") {
            val size = Size(10)
            size.value shouldBe 10
        }

        test("size 크기 검증") {
            val exception = shouldThrow<IllegalArgumentException> { Size(-1) }
            exception.message shouldBe "size는 0보다 커야 합니다."
        }
    }
})
