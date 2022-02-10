import Button1 from 'react-bootstrap/Button'

const Button = (props) => {
    return (
        <Button1 onClick = {props.onClick} variant = {props.variant} className = {props.className2} >
            <h4>
                {props.text}
            </h4>
        </Button1>
    )
}

export default Button
