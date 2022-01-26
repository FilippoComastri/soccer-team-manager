package component.dialogs

import Match
import csstype.*
import kotlinext.js.jso
import mui.material.*
import mui.system.ResponsiveStyleValue
import org.w3c.dom.HTMLInputElement
import react.Props
import react.ReactNode
import react.dom.onChange
import react.fc
import react.useState

external interface MatchDialogProps : Props {
    var isOpenDialog: Boolean
    var submitFormAddingMatch: (Match) -> Unit
    var closeForm: () -> Unit
}

val matchDialog = fc<MatchDialogProps> { props ->
    var opponentValue by useState("")
    var played by useState(false)
    var madeGoals by useState(-1)
    var concededGoals by useState(-1)
    var home by useState(false)
    var dayHourValue by useState("")

    Dialog {
        attrs {
            open = props.isOpenDialog
        }
        DialogTitle {
            +"Insert new Match"
        }
        DialogContent {
            Stack {
                attrs {
                    sx = jso {
                        width = 200.px
                        display = Display.flex
                        flexWrap = FlexWrap.nowrap
                        justifyContent = JustifyContent.spaceAround
                        alignItems = AlignItems.stretch
                        spacing = ResponsiveStyleValue(1.5)
                    }
                    direction = ResponsiveStyleValue(StackDirection.column)
                }

                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Opponent")
                        variant = FormControlVariant.filled
                        onChange = {
                            opponentValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                Stack {
                    attrs {
                        sx = jso {
                            spacing = ResponsiveStyleValue(1)
                            flexGrow = FlexGrow(1.0)
                        }
                        direction = ResponsiveStyleValue(StackDirection.row)
                    }
                    +"Home "
                    Checkbox {
                        attrs {
                            checked = home
                            onChange = { event, _ ->
                                home = event.target.checked
                            }
                        }
                    }
                    +"Played"
                    Checkbox {
                        attrs {
                            checked = played
                            onChange = { event, _ ->
                                played = event.target.checked
                            }
                        }
                    }
                }
                if (played) {
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
                                       console.log("Parsing Error")
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
                                        //implementare comportamento errore
                                    }
                                }
                            }
                        }
                    }
                }
                TextField {
                    attrs {
                        helperText = ReactNode("Date and Hour")
                        variant = FormControlVariant.filled
                        onChange = {
                            dayHourValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
            }
        }
        DialogActions {
            Button {
                attrs {
                    onClick = { props.closeForm() }
                }
                +"Cancel"
            }
            Button {
                attrs {
                    onClick = {
                        props.submitFormAddingMatch(
                            Match(
                                opponentValue,
                                played,
                                madeGoals,
                                concededGoals,
                                home,
                                dayHourValue
                            )
                        )
                    }
                }
                +"Submit"
            }
        }
    }
}