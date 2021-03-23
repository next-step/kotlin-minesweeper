package userinterface

import dto.MineSweeperInitDto

object Console : UserInterface {
    override fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto {
        val height = inputInt("높이를 입력하세요.")
        val width = inputInt("너비를 입력하세요.")
        val mineCount = inputInt("지뢰는 몇 개인가요?")

        return (MineSweeperInitDto(height = height, width = width, mineCount = mineCount))
    }

    private fun inputInt(message: String): Int {
        println(message)
        return readLine()
            ?.trim()
            ?.toInt()
            ?: inputInt(message)
    }
}
