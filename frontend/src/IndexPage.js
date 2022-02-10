import { React, useState } from "react";
import Header from "./components/Login/Header1";
import Navbar1 from "./components/Navbar1";
import BeitragDeck from "./components/Browsing/BeitragDeck";

const IndexPage = () => {
  const [beitraege, setBeitraege] = useState(undefined);
  if ((beitraege === undefined)) { 
    console.error("hierfetch")
    fetchdata("Beitraege/Entdecke/")
      .then((response) => response.json())
      .then((data) => {
        setBeitraege(data.beitraege);
        console.log(data.beitraege)
      }).catch(function(data){
        console.error(data)
      })
      setBeitraege(null);
  }
  
  return (
    <div>
      <Navbar1 className="m-0"></Navbar1>
      <Header str="Entdecke" className="m-0"></Header>

      <BeitragDeck data = {beitraege}></BeitragDeck>
    </div>
  );
};

export default IndexPage;

export  function fetchdata(url) {
  return fetch(sessionStorage.ip + url, {
    headers: {
      "Content-Type": "application/json",
      charset: "UTF-8",
      Authorization: "Bearer " + sessionStorage.jwt,
    },
  });
}