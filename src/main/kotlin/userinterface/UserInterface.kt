package userinterface

import dto.MineSweeperInitDto

interface UserInterface {
    fun inputMineSweeperWidthHeightCount(): MineSweeperInitDto
}
