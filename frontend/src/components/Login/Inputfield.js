import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

const Inputfield = (props) => {
  

  let ohneueberschrift = props.ueberschrift;
  if (ohneueberschrift)
    return (
      <div className="App m-4">
        <Row className={props.className2}>
          <Col className="col-md-3">
            <Form.Label>
              <h3>{props.ueberschrift}</h3>
            </Form.Label>
          </Col>
          <Col className="col-md-9">
            <Form.Control
              id={props.id2}
              type={props.type}
              placeholder={props.placeholder}
            />
          </Col>
        </Row>
      </div>
    );
  else {
    return (
      <div className="App p-0 ">
        <Form.Control
          id={props.id2}
          type={props.type}
          placeholder={props.placeholder}
          className=""
        />
      </div>
    );
  }
};

export default Inputfield;
