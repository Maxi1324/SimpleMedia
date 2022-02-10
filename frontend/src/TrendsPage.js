import { React, useState } from "react";
import Header from "./components/Login/Header1";
import Navbar1 from "./components/Navbar1";
import BeitragDeck from "./components/Browsing/BeitragDeck";
const TrendsPage = () => {
  const [beitraege, setBeitraege] = useState(undefined);
  if (beitraege === undefined || beitraege.forEach === undefined) {

    fetch(sessionStorage.ip + "Beitraege/Trends")
      .then((response) => response.json())
      .then((data) =>  setBeitraege(data.beitraege));
  }

  return (
    <div>
      <div className="hintergrund hide" id="hintergrund"></div>
      <Navbar1 className="m-0"></Navbar1>
      <Header str="Trends" className="m-0"></Header>
      <BeitragDeck data={beitraege}></BeitragDeck>
    </div>
  );
};

export default TrendsPage;
