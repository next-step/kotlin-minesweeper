package view

import domain.Game
import vo.Height
import vo.MineCount
import vo.Width

class GameInputView(private val io: IO) {

    tailrec fun initGame(): Game =
        runCatching {
            io.write("높이를 입력하세요.")
            val height = readHeight()

            io.write("")
            io.write("너비를 입력하세요.")
            val width = readWidth()

            io.write("")
            io.write("지뢰는 몇 개인가요?")
            val mineCount = readMineCount()

            return Game.of(width, height, mineCount)
        }.getOrElse {
            io.write("잘못된 입력입니다.")
            return initGame()
        }

    private tailrec fun readHeight(): Height =
        runCatching {
            Height(io.read().toInt())
        }.getOrElse {
            io.write("다시 입력해주세요.")
            return readHeight()
        }

    private tailrec fun readWidth(): Width =
        runCatching {
            Width(io.read().toInt())
        }.getOrElse {
            io.write("다시 입력해주세요.")
            return readWidth()
        }

    private tailrec fun readMineCount(): MineCount =
        runCatching {
            MineCount(io.read().toInt())
        }.getOrElse {
            io.write("다시 입력해주세요.")
            return readMineCount()
        }
}
