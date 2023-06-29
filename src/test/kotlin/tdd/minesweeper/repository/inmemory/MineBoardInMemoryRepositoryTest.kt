package tdd.minesweeper.repository.inmemory

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.MineBoard
import tdd.minesweeper.fixture.create3x3Rows
import tdd.minesweeper.fixture.create5x5Rows
import tdd.minesweeper.repository.MineBoardRepository

class MineBoardInMemoryRepositoryTest : FunSpec({
    val repository: MineBoardRepository = MineBoardInMemoryRepository

    beforeEach {
        repository.clear()
    }

    test("식별자 정보가 존재하고 리포지토리에 없는 경우 새로 저장하고 식별자를 반환한다.") {
        val sourceBoard = MineBoard(area = Area(3, 3), rows = create3x3Rows())
        val savedId = repository.save(sourceBoard)

        val foundBoard = repository.find(savedId)

        foundBoard shouldBe sourceBoard
    }

    test("식별자 정보가 존재하고 리포지토리에 있는 경우 덮어쓰고 식별자를 반환한다.") {
        val sourceBoard = MineBoard(area = Area(3, 3), rows = create3x3Rows())
        val createdId = repository.save(sourceBoard)
        repository.save(MineBoard(id = createdId, area = Area(5, 5), rows = create5x5Rows()))

        val actual = repository.find(1)

        actual.getRemainCount() shouldBe 21
    }

    test("존재하지 않는 식별자 정보로 찾으려 할 경우 예외를 던진다.") {
        shouldThrow<NoSuchElementException> {
            repository.find(9)
        }
    }
})

private fun MineBoardRepository.clear() {
    (this as MineBoardInMemoryRepository).clear()
}
