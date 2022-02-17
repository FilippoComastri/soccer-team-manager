package component.dialogs

import Match
import MatchPlayerStatistics
import Player
import csstype.*
import kotlinext.js.jso
import kotlinx.browser.document
import mui.material.*
import mui.system.ResponsiveStyleValue
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.onChange
import react.fc
import react.useState

external interface playerStatDialogProps : Props {
    var isOpenDialog: Boolean
    var submitForm: (MatchPlayerStatistics) -> Unit
    var closeForm: () -> Unit
    var matches: List<Match>
    var players: List<Player>
}

val playerStatDialog= fc<playerStatDialogProps> {   props ->

    var madeGoals by useState(-1)
    var concededGoals by useState(-1)
    var playedMins by useState(-1)
    var vote by useState(-1.1)
    var yellowCards by useState(-1)
    var redCard by useState(-1)

    Dialog {
        attrs {
            open = props.isOpenDialog
        }
        DialogTitle {
            +"Insert player performance"
        }
        DialogContent {
            Stack {
                attrs {
                    sx = jso {
                        width = 360.px
                        display = Display.flex
                        flexWrap = FlexWrap.nowrap
                        justifyContent = JustifyContent.spaceAround
                        alignItems = AlignItems.stretch
                        spacing = ResponsiveStyleValue(1.5)
                    }
                    direction = ResponsiveStyleValue(StackDirection.column)
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

                    select {
                        attrs.id="matches"
                        for (m in props.matches) {
                            option {
                                attrs.value = m.id.toString()
                                +" vs ${m.opponent} - ${m.dayHour}"
                            }
                        }
                    }
                    select {
                        attrs.id="players"
                        for (p in props.players) {
                            option {
                                attrs.value = p.id.toString()
                                +" ${p.surname}"
                            }
                        }
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
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Made Goals")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    madeGoals = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
                    }
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Conceded Goals")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    concededGoals = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
                    }
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Played Mins")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    playedMins = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
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
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Vote")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    vote = (it.target as HTMLInputElement).value.toDouble()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
                    }
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Yellow Cards")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    yellowCards = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
                    }
                    TextField {
                        attrs {
                            sx = jso {
                                flexGrow = FlexGrow(1.0)
                            }
                            helperText = ReactNode("Red Cards")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    redCard = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.error("Parsing error")
                                }
                            }
                        }
                    }
                }
            }
        }
        DialogActions {
            Button {
                attrs {
                    style = jso() {
                        color = Color("#f2072e")
                        borderColor = Color("#20b02c")
                    }
                    onClick = { props.closeForm() }
                }
                +"Cancel"
            }
            Button {
                attrs {
                    style = jso() {
                        color = Color("#20b02c")
                        borderColor = Color("#20b02c")
                    }
                    onClick = {
                        props.submitForm(
                            MatchPlayerStatistics(
                                (document.getElementById("matches") as HTMLSelectElement).value.toInt(),
                                (document.getElementById("players") as HTMLSelectElement).value.toInt(),
                                madeGoals,
                                concededGoals,
                                playedMins,
                                vote,
                                yellowCards,
                                redCard
                            )
                        )
                    }
                }
                +"Submit"
            }
        }
    }
}
