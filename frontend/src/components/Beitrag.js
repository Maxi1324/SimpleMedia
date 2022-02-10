import { React, useState } from "react";
import Card from "react-bootstrap/Card";
import Image from "react-bootstrap/Image";
import { AiOutlineHeart, AiFillHeart } from "react-icons/ai";
import history from "../history";

const Beitrag = (props) => {
  const [like, setlike] = useState(undefined);
  const [first, setFirst] = useState(undefined);
  const [likes, setLikes] = useState(props.likes);
  if (
    first === undefined &&
    !(localStorage.aktNutzer === undefined || localStorage.aktNutzer === null)
  ) {
    postData(localStorage.ip + "hasLiked/?beitrag=" + props.name).then(
      (data) => {
        setlike(data.str === "true" ? true : false);
        setFirst(false);
      }
    );
  }
  if (!(localStorage.aktNutzer === undefined || localStorage.aktNutzer === null) && like === undefined ) return <h1> </h1>;
  let ersteller = props.ersteller;
  let key1 = props.key1;
  let element;
  if (!like) {
    element = (
      <AiOutlineHeart
        className="rechts Icon text-secondary"
        id={"herzaus" + key1}
        onClick={function () {
          postData(
            localStorage.ip + "like/?state=true&beitrag=" + props.name
          ).then((data) => {
            console.error(data); // JSON data parsed by `data.json()` call
            setlike(true);
            setLikes(likes + 1);
          });
        }}
      ></AiOutlineHeart>
    );
  } else {
    element = (
      <AiFillHeart
        className="rechts Icon text-danger"
        id={"herzan" + key1}
        onClick={function () {
          postData(
            localStorage.ip + "like/?state=false&beitrag=" + props.name
          ).then((data) => {
            setlike(false);
            setLikes(likes - 1);
          });
        }}
      ></AiFillHeart>
    );
  }

  return (
    <div>
      <Card className="mb-4">
        <Card.Header className="p-0 m-0 w-100">
          <div
            className="rechts "
            onClick={function () {
              history.push("/User" + ersteller.nutzername);
            }}
          >
            <img
              alt="profilbild"
              src={ersteller.profilBild}
              className="reallyRound center rechts"
            ></img>
            <h5 className="text-center"> {ersteller.nutzername}</h5>
          </div>
          <h1 className="m-3">{props.name}</h1>
        </Card.Header>
        <Image className="w-50 border center" src={props.img}></Image>
        <Card.Body>
          <p className="text1">{props.text}</p>
        </Card.Body>
        <Card.Footer>
          <div className="rechts text-center">
            {element}
            <div id="likes">{likes}</div>
          </div>
        </Card.Footer>
      </Card>
    </div>
  );
};

async function postData(url = "") {
  // Default options are marked with *
  const response = await fetch(url, {
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    mode: "cors", // no-cors, *cors, same-origin
    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
    credentials: "same-origin", // include, *same-origin, omit
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.jwt,
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: "follow", // manual, *follow, error
    referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
  });
  return response.json(); // parses JSON response into native JavaScript objects
}

export default Beitrag;
