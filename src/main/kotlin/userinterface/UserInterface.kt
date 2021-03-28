package userinterface

import dto.MineBoardDto
import dto.MineSweeperInitDto

interface UserInterface {
    fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto
    fun outputMineSweeper(mineBoardDto: MineBoardDto)
}
