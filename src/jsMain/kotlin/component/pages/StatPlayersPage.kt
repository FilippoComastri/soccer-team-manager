package component.pages

import Match
import Player
import addPlayer
import addTraining
import component.cardPlayer
import component.dialogs.insertPlayerDialog
import component.dialogs.playerStatDialog
import component.playersTable
import csstype.*
import getMatchList
import getPlayers
import getTrainingList
import kotlinext.js.jso
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.icons.material.AddRounded
import mui.material.*
import mui.system.ResponsiveStyleValue
import mui.system.Spacing
import react.*
import updatePlayer


val statPlayer = fc<Props> {
    var currentPlayer: Player? by useState(null)
    var playerss by useState(emptyList<Player>())
    var matchess by useState(emptyList<Match>())
    var isOpenInsertPlayerDialog by useState(false)
    var isOpenStatDialog by useState(false)

    useEffectOnce {
        MainScope().launch {
            playerss=getPlayers()
            matchess=getMatchList()
        }
    }

    Box {
        attrs {
            sx = jso {
                display = Display.flex
                flexWrap = FlexWrap.nowrap
            }
        }
        Stack {
            attrs {
                sx = jso {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    flexWrap = FlexWrap.nowrap
                    spacing = ResponsiveStyleValue(5)
                    alignItems = AlignItems.center
                }
            }
            child(playersTable) {
                attrs {
                    onSelectPlayer = { player -> currentPlayer = player }
                    players = playerss
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.flex
                        flexWrap = FlexWrap.nowrap
                        spacing = ResponsiveStyleValue(1)
                        flexGrow = FlexGrow(1.0)
                    }
                    direction = ResponsiveStyleValue(StackDirection.row)
                }
                Button {
                    attrs {
                        variant = ButtonVariant.outlined
                        onClick = { isOpenInsertPlayerDialog=true }
                    }
                    +"Add Player"
                    AddRounded()
                }
                Button {
                    attrs {
                        variant = ButtonVariant.outlined
                        onClick = { isOpenStatDialog=true }
                    }
                    +"Add Player Statistics"
                    AddRounded()
                }
            }


        }

        currentPlayer?.let { curr ->
            child(cardPlayer) {
                attrs {
                    player = curr
                }
            }
        }
    }
    child(insertPlayerDialog) {
        attrs {
            isOpenDialog = isOpenInsertPlayerDialog
            closeForm = { isOpenInsertPlayerDialog=false }
            submitForm = { p ->
                MainScope().launch {
                    addPlayer(p)
                    playerss = getPlayers()
                }
                isOpenInsertPlayerDialog = false
            }
        }
    }

    child(playerStatDialog) {
            attrs {
                isOpenDialog=isOpenStatDialog
                closeForm={ isOpenStatDialog=false }
                matches=matchess
                players=playerss
                submitForm = {  stat ->
                    MainScope().launch {
                        updatePlayer(stat)
                        playerss=getPlayers()
                    }
                    isOpenStatDialog=false
                }
            }
    }

}