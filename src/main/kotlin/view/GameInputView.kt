package view

import domain.Game
import domain.vo.Height
import domain.vo.MineCount
import domain.vo.Width

class GameInputView(private val io: IO) {

    tailrec fun initGame(): Game =
        runCatching {
            io.writeLn("높이를 입력하세요.")
            val height = readHeight()

            io.writeLn()
            io.writeLn("너비를 입력하세요.")
            val width = readWidth()

            io.writeLn()
            io.writeLn("지뢰는 몇 개인가요?")
            val mineCount = readMineCount()

            return Game.of(width, height, mineCount)
        }.getOrElse {
            io.writeLn("잘못된 입력입니다.")
            return initGame()
        }

    private fun readInt() = io.read().toInt()

    private tailrec fun readHeight(): Height {

        return runCatching {
            Height(readInt())
        }.getOrElse {
            io.writeLn("다시 입력해주세요.")
            return readHeight()
        }
    }

    private tailrec fun readWidth(): Width =
        runCatching {
            Width(readInt())
        }.getOrElse {
            io.writeLn("다시 입력해주세요.")
            return readWidth()
        }

    private tailrec fun readMineCount(): MineCount =
        runCatching {
            MineCount(readInt())
        }.getOrElse {
            io.writeLn("다시 입력해주세요.")
            return readMineCount()
        }
}
