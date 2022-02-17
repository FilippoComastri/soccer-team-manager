package component.HomePage

import Match
import component.lineup.*
import csstype.*
import react.*
import mui.material.*
import kotlinext.js.jso
import mui.icons.material.DeleteForever
import mui.icons.material.Edit


external interface MatchProps : Props {
    var match: Match
    var myTeam: String
    var openChangeDialog: () -> Unit
    var deleteMatch : () -> Unit
}

val matchView = fc<MatchProps> { props ->
    Box {
        attrs {
            sx = jso {
                width = 100.pct
            }
        }
        Card {
            attrs {
                sx = jso {
                    display =Display.block
                    width = 100.pct
                    maxHeight=180.px
                    backgroundColor = Color("#20b02c")
                    color = Color("White")
                }
            }
            CardHeader {
                attrs{
                    when {
                        props.match.home && props.match.played-> title = ReactNode("${props.myTeam}  ${props.match.madeGoals} : ${props.match.concededGoals}    ${props.match.opponent}")
                        !props.match.home && props.match.played-> title = ReactNode("${props.match.opponent}  ${props.match.concededGoals} :  ${props.match.madeGoals}  ${props.myTeam} ")
                        props.match.home && !props.match.played -> title = ReactNode("${props.myTeam} vs ${props.match.opponent}  ")
                        !props.match.home && !props.match.played -> title = ReactNode("${props.match.opponent} vs ${props.myTeam} ")
                    }
                    subheader = ReactNode("${props.match.dayHour}")
                }
            }
            CardContent {
                IconButton {
                        attrs.sx = jso {
                            color = Color("White")
                        }
                        attrs.onClick = {
                            props.openChangeDialog()
                        }
                        Edit()
                    }
                IconButton {
                        attrs {
                            style = jso {
                                color = Color("#f2072e")
                                borderColor = Color("#f2072e")
                            }
                            onClick = {
                                props.deleteMatch()
                            }
                        }
                        DeleteForever()
                }
            }

        }
    }
}