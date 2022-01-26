package component

import Match
import csstype.Color
import io.ktor.http.*
import react.*
import react.dom.*
import kotlinx.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.*
import kotlinext.js.*
import react.dom.html.ReactHTML
import csstype.Display
import csstype.HtmlAttributes
import csstype.px
import kotlinext.js.jso
import csstype.pct
import mui.icons.material.Edit


external interface MatchProps : Props {
    var match: Match
    var myTeam: String
    var openChangeDialog: () -> Unit
}

val matchView = fc<MatchProps> { props ->
    Card {
        attrs {
            sx = jso {
                display = Display.inlineBlock
                width = 100.pct
                height = 100.px
                backgroundColor = Color("AliceBlue")
                color = Color("Black")
            }
        }
        CardContent {
            if(!props.match.played) {
                Typography {
                    attrs {
                        gutterBottom = true
                        align = TypographyAlign.center
                        variant = "body2"
                        component = ReactHTML.div
                        sx = jso {
                            display = Display.inlineBlock
                        }
                    }
                        +"${props.match.dayHour}"
                }
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
            IconButton {
                attrs {
                    onClick = {
                        props.openChangeDialog()
                    }
                }
                Edit()
            }

        }
    }
}