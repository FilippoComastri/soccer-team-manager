package component.dialogs

import Match
import component.pages.home
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

external interface InsertResultDialogProps : Props {
    var isOpenDialog: Boolean
    var submitForm: (Match) -> Unit
    var closeForm: () -> Unit
    var match : Match
    var myTeam : String
}

val insertResultDialog = fc<InsertResultDialogProps> {  props ->
    var madeGoalsValue by useState(0)
    var concededGoalsValue by useState(0)

    Dialog {
        attrs {
            open = props.isOpenDialog
        }
        DialogTitle {
            +"${props.myTeam} vs ${props.match.opponent}"
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
                DialogContentText {
                    +"${props.match.dayHour}"
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
                                        madeGoalsValue = (it.target as HTMLInputElement).value.toInt()
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
                                        concededGoalsValue = (it.target as HTMLInputElement).value.toInt()
                                    } catch (e: NumberFormatException) {
                                        //implementare comportamento errore
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
                    onClick = { props.closeForm() }
                }
                +"Cancel"
            }
            Button {
                attrs {
                    onClick = {
                        props.submitForm(Match(
                            props.match.opponent,
                            true,
                            madeGoalsValue,
                            concededGoalsValue,
                            props.match.home,
                            props.match.dayHour
                        ))
                    }
                }
                +"Submit"
            }
        }
    }
}