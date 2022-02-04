package component.HomePage

import Match
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
                    backgroundColor = Color("AliceBlue")
                    color = Color("Black")
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
                        attrs.onClick = {
                            props.openChangeDialog()
                        }
                        Edit()
                    }
                IconButton {
                        attrs {
                            onClick = {
                                props.deleteMatch()
                            }
                        }
                        DeleteForever()
                }
            }

        }
    }
    /*Card {
        attrs {
            sx = jso {
                width = 100.pct
                height = 100.px
                backgroundColor = Color("AliceBlue")
                color = Color("Black")
            }
        }
        Box {
            attrs {
                sx = jso {
                    display = Display.tableColumn
                    flexWrap = FlexWrap.nowrap
                }
            }
            CardContent {
                attrs {
                    sx = jso {
                        display = Display.block
                        flexGrow = FlexGrow(5.0)
                    }
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.center
                        variant = "body4"
                        component = ReactHTML.div
                        sx = jso {
                            display = Display.inlineBlock
                        }
                    }
                    +"${props.match.dayHour}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.center
                        variant = "subtitle1"
                        component=ReactHTML.div
                        sx = jso {
                            display= Display.inlineBlock
                        }
                    }
                    when {
                        props.match.home && props.match.played-> + "${props.myTeam}  ${props.match.madeGoals} : ${props.match.concededGoals}    ${props.match.opponent}"
                        !props.match.home && props.match.played-> + "${props.match.opponent}  ${props.match.concededGoals} :  ${props.match.madeGoals}  ${props.myTeam} "
                        props.match.home && !props.match.played -> + "${props.myTeam} vs ${props.match.opponent}  "
                        !props.match.home && !props.match.played -> + "${props.match.opponent} vs ${props.myTeam} "
                    }

                }

            }

        }
    }*/
}