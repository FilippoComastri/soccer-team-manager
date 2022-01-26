package component

import Player
import csstype.*
import kotlinext.js.jso
import mui.material.*
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML
import react.fc

external interface CardPlayerProps : Props {
    var player : Player
}

val cardPlayer = fc<CardPlayerProps> { props ->
    Box {
        attrs {
            sx = jso {
                flexGrow = FlexGrow(1.0)
                padding = 10.px
                //marginLeft = 10.px
                marginBottom = 10.px
            }
        }
        Card {
            attrs {
                sx = jso {
                    display =Display.block
                    width = 100.pct
                    backgroundColor = Color("AliceBlue")
                    color = Color("Black")
                }
            }
            CardHeader {
                attrs{
                    title = ReactNode("${props.player.name} ${props.player.surname}")
                    subheader = ReactNode("${props.player.nationality} - ${props.player.dateOfBirth}")
                }
            }
            CardContent {
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    var roleString = "Roles : "
                    for (role in props.player.roles) {
                        roleString+=role
                        roleString+=" "
                    }
                    +"${roleString}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    if(props.player.roles.get(0)=="POR"){
                        +"Conceded Goals: ${props.player.concededGoals}"
                    } else {
                        +"Made Goals: ${props.player.madeGoals}"
                    }
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    +"Played Mins: ${props.player.playedMins}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    +"Played Matches: ${props.player.playedMatches}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    +"Yellow Cards: ${props.player.yellowCards}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    +"Red Cards: ${props.player.redCards}"
                }
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.left
                        variant = "body1"
                        component = ReactHTML.div
                    }
                    +"Characteristics: ${props.player.characteristics}"
                }

            }
        }
    }
}