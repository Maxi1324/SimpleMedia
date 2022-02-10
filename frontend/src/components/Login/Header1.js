import React from "react";
import Jumbotron from "react-bootstrap/Jumbotron";

const Header1 = (props) => {
  return (
    <div className="App">
      <Jumbotron className="jumbotron-fluid text-center header p-5 pl-6 pr-6">
        <h2 className=" border inline p-2 pl-3 pr-3 text-white">{props.str}</h2>
      </Jumbotron>
    </div>
  );
};

export default Header1;
