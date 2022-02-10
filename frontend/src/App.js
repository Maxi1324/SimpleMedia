import "bootstrap/dist/css/bootstrap.min.css";
import "./css/style.css";
import {
  Router,
  Redirect,
  Switch,
  Route,
} from "react-router-dom";

import history from './history';

import Index from "./IndexPage";
import ErrorPage from "./ErrorPage";
import User from "./UserPage";
import Trends from "./TrendsPage";
import { React, useState } from "react";

function App() {
  window.sessionStorage.ip = "http://localhost:8080/";
  return (
    <>
    <div className="hintergrund hide" id="hintergrund"></div>
    <Router history = {history}>
      <Switch>
        <Route exact path="/" component={Index} />
        <Route exact path="/User:user" component={User} />
        <Route exact path="/Trends" component={Trends} />
        <Route exact path="/404" component={ErrorPage}></Route>
        <Redirect to="/404"></Redirect>
      </Switch>
    </Router>
    </>
  );
}

export function useForceUpdate(){
  const [value, setValue] = useState(0); // integer state
  return () => setValue(value => value + 1); // update the state to force render
}

export default App;
