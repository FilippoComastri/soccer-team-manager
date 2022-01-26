package component.dialogs

import Match
import Player
import csstype.*
import kotlinext.js.jso
import mui.material.*
import mui.system.ResponsiveStyleValue
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import react.Props
import react.ReactNode
import react.dom.onChange
import react.fc
import react.useState

external interface InserPlayerDialogProps : Props {
    var isOpenDialog: Boolean
    var submitForm: (Player) -> Unit
    var closeForm: () -> Unit
}

val insertPlayerDialog = fc<InserPlayerDialogProps> {   props ->
    var shirtNumberValue by useState(-1)
    var nameValue by useState("")
    var surnameValue by useState("")
    var rolesValue by useState("")
    var nationalityValue by useState("")
    var dateOfBirthValue by useState("")
    var marketValueVal by useState(-1)
    var characteristicsValue by useState("")

    Dialog {
        attrs {
            open = props.isOpenDialog
        }
        DialogTitle {
            +"Inser a new Player"
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
                        helperText = ReactNode("Name")
                        variant = FormControlVariant.filled
                        onChange = {
                            nameValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Surname")
                        variant = FormControlVariant.filled
                        onChange = {
                            surnameValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Nationality")
                        variant = FormControlVariant.filled
                        onChange = {
                            nationalityValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Date of Birth")
                        variant = FormControlVariant.filled
                        onChange = {
                            dateOfBirthValue = (it.target as HTMLInputElement).value
                        }
                    }
                }
                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Roles")
                        variant = FormControlVariant.filled
                        onChange = {
                            rolesValue = (it.target as HTMLInputElement).value
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
                            helperText = ReactNode("Shirt Number")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    shirtNumberValue = (it.target as HTMLInputElement).value.toInt()
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
                            helperText = ReactNode("Market Value")
                            variant = FormControlVariant.filled
                            onChange = {
                                try {
                                    marketValueVal = (it.target as HTMLInputElement).value.toInt()
                                } catch (e: NumberFormatException) {
                                    console.log("Parsing Error")
                                }
                            }
                        }
                    }

                }
                TextField {
                    attrs {
                        sx = jso {
                            flexGrow = FlexGrow(1.0)
                        }
                        helperText = ReactNode("Characteristics")
                        variant = FormControlVariant.filled
                        multiline = true
                        minRows = 5
                        maxRows = 5
                        onChange = {
                            characteristicsValue = (it.target as HTMLTextAreaElement).value
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
                        val rolesList = rolesValue.split(",")
                        props.submitForm(Player(
                            shirtNumberValue,
                            nameValue,
                            surnameValue,
                            rolesList,
                            0,
                            0  ,
                            0,
                            0,
                            0.0,
                            nationalityValue,
                            dateOfBirthValue, //
                            marketValueVal, //
                            characteristicsValue,
                            0 ,
                            0 ,
                            false,
                            false,
                            0,
                            mutableListOf()
                        ))
                    }
                }
                +"Submit"
            }
        }
    }
}