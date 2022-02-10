import { React, useState } from "react";
import { Container } from "react-bootstrap";
import BeitragDeck from "./components/Browsing/BeitragDeck";
import Header1 from "./components/Login/Header1";
import { Navbar1 } from "./components/Navbar1";
import { fetchdata } from "./IndexPage";

import { useParams } from "react-router-dom";

let user;

const UserPage = () => {
  const [beitraege, setBeitraege] = useState(undefined);

  let parameter = useParams();

  if (beitraege === undefined || beitraege.forEach === undefined) {
    fetchdata("Beitraege/User/?Page=0&User=" + parameter.user)
      .then((response) => response.json())
      .then((data) => {
        user = data.user;
        setBeitraege(data.beitraege.beitraege);
      });
    return <h3>noch nicht geladen</h3>;
  }
  let gesch = "Mänlich";
  if (user.geschlecht === 1) gesch = "Weiblich";
  if (user.geschlecht === 2) gesch = "undefiniert";

  return (
    <div>
      <Navbar1 className="m-0"></Navbar1>
      <Header1 str="Nutzer" className="m-0"></Header1>
      <img
        className="border p-0 center w-25 mt-4"
        src={user.profilBild}
        alt="profilPic"
      ></img>

      <Container className="border mt-5">
        <p>
          User: {user.nutzername}
          <br />
          E-mail: {user.mail}
          <br />
          Geschlecht: {gesch}
        </p>
      </Container>

      <BeitragDeck className2="mt-5" data={beitraege}></BeitragDeck>
    </div>
  );
};

export default UserPage;
