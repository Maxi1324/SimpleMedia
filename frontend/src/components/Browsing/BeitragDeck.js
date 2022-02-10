import React from "react";
import { Container } from "react-bootstrap";
import Beitrag from "../Beitrag";

var beitraegesum;
const BeitragDeck = (props) => {
  if(props === undefined)return <h3 className="m-5">Beitraege nicht geladen</h3>;
  let beitraege = props.data;
  if (beitraege === undefined || beitraege === 0 || beitraege === null)
    return <h3 className="m-5">Beitraege nicht geladen</h3>;
  if (sessionStorage.aktNutzer === undefined) beitraegesum = undefined;
  if (beitraegesum === null || beitraegesum === undefined)
    beitraegesum = beitraege;
  else if (beitraege === undefined || beitraege.forEach !== undefined) {
    beitraege.forEach((element) => {
      let vorhanden;
      beitraegesum.forEach((element2) => {
        if (
          element.ueberschrift === element2.ueberschrift &&
          element.text === element2.text
        )
          vorhanden = true;
      });
      if (!vorhanden) beitraegesum.push(element);
    });
  } else {
    console.error("ffehler  ");
  }
  return (
    <Container className={props.className2}>
      {beitraegesum.map((e, i) => {
        return (
          <Beitrag
            name={e.ueberschrift}
            text={e.text}
            img={e.img}
            ersteller={e.ersteller}
            key1={i}
            likes={e.likes}
          />
        );
      })}
    </Container>
  );
};

export function resetBeitrag(){
  beitraegesum = undefined;
}

export default BeitragDeck;
