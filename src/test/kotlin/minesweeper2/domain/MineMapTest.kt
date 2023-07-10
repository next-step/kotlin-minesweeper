package minesweeper2.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper2.domain.Length
import minesweeper2.domain.MineCount
import minesweeper2.domain.MineMap

private fun Int.toHeight() = Length.of(this.toString())
private fun Int.toWidth() = Length.of(this.toString())
private fun Int.toCount() = MineCount.of(this.toString())
