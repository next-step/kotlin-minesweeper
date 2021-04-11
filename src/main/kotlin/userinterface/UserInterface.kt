package userinterface

import dto.MineGameDto
import dto.MineSweeperInitDto

interface UserInterface {
    fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto
    fun outputMineSweeper(mineGameDto: MineGameDto)
    fun inputCheckCoordinate(): Pair<Int, Int>
}
