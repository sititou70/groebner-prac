import React from "react";
import ReactDOM from "react-dom/client";
// @ts-ignore
import { groebnerBasis } from "../groebner/build/js/packages/groebner-composeApp";
console.log(groebnerBasis());

const Sample = () => "sample";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <Sample />
  </React.StrictMode>
);
