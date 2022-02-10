import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Button from "./Login/Button";
import Inputfield from "./Login/Inputfield";
import { Card, Container } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import history from "../history";
import ImageUpload from "./ImageUpload";
import { AiOutlineUser } from "react-icons/ai";
import { React, useState } from "react";
import { useForceUpdate } from "../App";
import resetBeitrag from "./Browsing/BeitragDeck";

var lastInFront = "";
var callback;

var ip = window.sessionStorage.ip;
export const Navbar1 = () => {
  document.addEventListener("keydown", function (event) {
    if (event.code === "Escape") {
      hideinFront();
    }
  });
  let img;
  let canvas;

  const [user, setUser] = useState(undefined);
  const [logErrMess, setlogErrMess] = useState(undefined);
  if (user === undefined && sessionStorage.aktNutzer !== undefined) {
    fetch(
      sessionStorage.ip +
        "Beitraege/getUserProfilbild/?User=" +
        sessionStorage.aktNutzer
    )
      .then((response) => response.json())
      .then((data) => {
        setUser(data);
      })
      .catch(function (e) {
        console.error(e);
      });
  }

  return (
    <div>
      <Navbar bg="black" variant="dark " className="black">
        <Navbar.Brand href="">
          {user === undefined || user === null ? (
            <AiOutlineUser
              className="Icon"
              onClick={function () {
                showInFront("loginOrRegist");
              }}
            ></AiOutlineUser>
          ) : (
            <>
              <img
                src={user.profilBild}
                alt="profilbild"
                className=" m-0 p-0 reallyRound profilbild"
                onClick={function () {
                  history.push("/User" + sessionStorage.aktNutzer);
                }}
              ></img>
            </>
          )}
        </Navbar.Brand>
        <Nav className="mr-auto">
          <div
            onClick={function () {
              resetBeitrag();
              history.push("/");
              resetBeitrag();
            }}
          >
            <Nav.Link>Entdecke</Nav.Link>
          </div>
          <LinkContainer className="" to="/Trends">
            <Nav.Link
              onClick={function () {
               
              }}
            >
              Trends
            </Nav.Link>
          </LinkContainer>
        </Nav>
        {user === undefined || user === null ? (
          <></>
        ) : (
          <>
            <Button
              text="logout"
              className2="h-100 m-0 rechts mr-1 navbut"
              variant="secondary"
              onClick={function () {
                window.location.reload();
                sessionStorage.clear();
              }}
            ></Button>
            <Button
              text="Post"
              className2="h-100 m-0 rechts navbut"
              variant="secondary"
              onClick={function () {
                showInFront("newPost", function () {});
              }}
            ></Button>
          </>
        )}
      </Navbar>

      <Container className="border p-1 hide col-md-2" id="loginOrRegist">
        <Button
          variant="secondary"
          text="Login"
          className2="w-100 mb-2"
          onClick={function () {
            showInFront("login");
          }}
        ></Button>
        <Button
          variant="secondary"
          text="Registrieren"
          className2="w-100"
          onClick={function () {
            showInFront("regis");
          }}
        ></Button>
      </Container>

      <Container className="border p-4 hide" id="regis">
        <Inputfield
          ueberschrift="Username"
          type="text"
          id2="username2"
          placeholder="zb. beispielname"
        ></Inputfield>
        <Inputfield
          ueberschrift="Passwort:"
          type="password"
          id2="passwort"
          placeholder="zb. beispielpasswort"
        ></Inputfield>
        <Inputfield
          ueberschrift="E-mail:"
          type="email"
          id2="email"
          placeholder="zb. beispiel@beispiel.com"
          className2=""
        ></Inputfield>
        <div className="m-2 row mb-4 ">
          <h3 className="col-md-3 m-0">Geschlecht</h3>
          <div className="col-md-9 row pl-4">
            <p>
              <input type="radio" value="Male" name="gender" className="mr-1" />
              männlich
            </p>
            <p className="ml-2">
              <input
                type="radio"
                value="Female"
                name="gender"
                className="mr-1"
                id="frau"
              />
              weiblich
            </p>
            <p className="ml-2">
              <input
                type="radio"
                value="Other"
                name="gender"
                className="mr-1"
                id="anders"
              />
              diverse
            </p>
          </div>
        </div>
        <ImageUpload
          imgID="imgRegis"
          onChange={function (can) {
            img = can.toDataURL();
          }}
        ></ImageUpload>
        <Button
          variant="secondary"
          text="Registrieren"
          className2="w-100"
          onClick={function () {
            let username = document.getElementById("username2").value;
            let passwort = document.getElementById("passwort").value;
            let mail = document.getElementById("email").value;
            let geschlecht = 0;
            if (document.getElementById("frau").checked) {
              geschlecht = 1;
            } else if (document.getElementById("anders").checked) {
              geschlecht = 2;
            }
            postData(ip + "Account/registrieren", {
              nutzername: username,
              passwort: passwort,
              mail: mail,
              geschlecht: geschlecht,
              img: img,
            })
              .then(function (data) {
                showInFront("activate");
              })
              .catch(function (err) {
                console.error(err);
              });
          }}
        ></Button>
      </Container>

      <Container className="border p-4 hide" id="login">
        <Inputfield
          ueberschrift="Username"
          type="email"
          id2="username"
          placeholder="Username oder E-mail"
        ></Inputfield>
        <Inputfield
          ueberschrift="Passwort:"
          type="password"
          id2="Userpasswort"
          placeholder="Passwort"
        ></Inputfield>
        <p className="fehlermeld"></p>
        <Button
          variant="secondary"
          text="Login"
          onClick={function () {
            let passwort = document.getElementById("Userpasswort").value;
            let username = document.getElementById("username").value;
            postData(ip + "Account/auth", {
              username: username,
              passwort: passwort,
            })
              .then((data) => {
                console.error(data);
                sessionStorage.jwt = data.jwt;
                history.push("/User" + username);
                hideinFront();
                sessionStorage.aktNutzer = username;
              })
              .catch(function (e) {
                console.error(e);
              });
          }}
          className2="w-100"
        ></Button>
      </Container>

      <Container className="border p-4 hide" id="activate">
        <Inputfield
          ueberschrift="Code: "
          type="text"
          placeholder="We have sent you a Activation Code to your e-Mail"
          id2="code"
        ></Inputfield>
        <Button
          variant="secondary"
          text="Login"
          onClick={function () {
            let key = document.getElementById("code").value;
            let userName = document.getElementById("username2").value;
            postData(ip + "Account/activate", {
              key: key,
              userName: userName,
            })
              .then((data) => {
                postData(ip + "Account/auth", {
                  username: userName,
                  passwort: document.getElementById("passwort").value,
                })
                  .then((data2) => {
                    sessionStorage.jwt = data2.jwt;
                    history.push("/User" + userName);
                    hideinFront();
                    sessionStorage.aktNutzer = userName;
                  })
                  .catch(function (e) {
                    console.error(e);
                  });
                hideinFront();
              })
              .catch(function (err) {
                console.error(err);
              });
          }}
          className2="w-100"
        ></Button>
      </Container>

      <Container className="hide border" id="newPost">
        <Card className="nichtRund">
          <Card.Header className="p-0 m-0">
            <Inputfield
              placeholder="Postüberschrift"
              ohneueberschrift={true}
              id2="beitragueber"
              className="w-100  p-0 nichtRund"
              className2="p-1 nichtRund"
            ></Inputfield>
          </Card.Header>

          <ImageUpload
            imgID="imgRegis2"
            onChange={function (can) {
              canvas = can;
            }}
          ></ImageUpload>

          <Card.Footer className="p-0">
            <textarea
              className="form-control"
              placeholder="Posttext"
              rows="5"
              id="text"
            />
          </Card.Footer>
        </Card>
        <Button
          text="neuer Post"
          className2="w-100 mt-4"
          variant="secondary"
          onClick={function () {
            showInFront("ladehoch");
            convertToDataURLviaCanvas(canvas, function (base64Img) {
              let ueber = document.getElementById("beitragueber").value;
              let text = document.getElementById("text").value;
              postDataauth(sessionStorage.ip + "User/postBeitrag", {
                ueberschrift: ueber,
                text: text,
                img: base64Img,
              })
                .then(function () {
                  showInFront("hochgeladen");
                })
                .catch(function (err) {
                  console.error(err);
                });
            });
          }}
        ></Button>
      </Container>

      <Container className="border p-4 hide" id="hochgeladen">
        <h3>Beitrag wurde hochgeladen</h3>
        <Button
          text="Weiter"
          className="m-4"
          className2="m-2"
          variant="secondary"
          onClick={function () {
            hideinFront();
          }}
        ></Button>
      </Container>

      <Container className="border p-4 hide" id="ladehoch">
        <h3>Beitrag wird hochgeladen</h3>
      </Container>
    </div>
  );
};

export function showInFront(id, callback2) {
  hideinFront();
  let realID = id;
  if (document.getElementById(id) !== null) {
    document.getElementById(id).classList.remove("hide");
    document.getElementById(id).classList.add("inFront");
    document.getElementById("hintergrund").classList.remove("hide");
    document.getElementById("hintergrund").classList.add("inFront");
  }
  lastInFront = realID;
  window.addEventListener("resize", function () {
    centerElement(realID);
  });
  document.getElementById("hintergrund").addEventListener("click", function () {
    hideinFront();
  });

  callback = callback2;
  centerElement(realID);
}

export function hideinFront() {
  if (document.getElementById(lastInFront) !== null) {
    if (callback !== undefined) {
      callback();
    }
    document.getElementById(lastInFront).classList.add("hide");
    document.getElementById(lastInFront).classList.remove("inFront");
    document.getElementById("hintergrund").classList.add("hide");
    document.getElementById("hintergrund").classList.remove("inFront");
    lastInFront = null;
  }
}

function centerElement(id) {
  let x = window.innerWidth / 2;
  let ele = document.getElementById(id);
  if (ele === undefined) return;
  ele.style.right = x - ele.offsetWidth / 2 + "px";
  ele.style.top = window.pageYOffset + 100 + "px";
  document.getElementById("hintergrund").style.height =
    document.body.scrollHeight > window.innerHeight
      ? document.body.scrollHeight + "px"
      : window.innerHeight;
}
export default Navbar1;

async function postData(url = "", data = {}) {
  const response = await fetch(url, {
    method: "POST",
    mode: "cors",
    cache: "no-cache",
    credentials: "same-origin",
    headers: {
      "Content-Type": "application/json",
      charset: "UTF-8",
    },
    redirect: "follow",
    referrerPolicy: "no-referrer",
    body: JSON.stringify(data),
  });
  return response.json();
}

export async function postDataauth(url = "", data = {}) {
  const response = await fetch(url, {
    method: "POST",
    mode: "cors",
    cache: "no-cache",
    credentials: "same-origin",
    headers: {
      "Content-Type": "application/json",
      charset: "UTF-8",
      Authorization: "Bearer " + sessionStorage.jwt,
    },
    redirect: "follow",
    referrerPolicy: "no-referrer",
    body: JSON.stringify(data),
  });
  return response.json();
}

async function convertToDataURLviaCanvas(canvas, callback) {
  let dataURL;
  dataURL = canvas.toDataURL();
  callback(dataURL);
}
