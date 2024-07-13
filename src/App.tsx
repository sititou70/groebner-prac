import { FC, useEffect, useState } from "react";
import { InlineMath } from "react-katex";
import "katex/dist/katex.min.css";
import "./App.css";

// @ts-ignore
// prettier-ignore
import { groebnerBasisString, sampleQuestions } from "../groebner/build/js/packages/groebner-composeApp/kotlin/groebner-composeApp.mjs";

const questions: { title: string; basis: string }[] = sampleQuestions
  .get()
  .split("---")
  .map((x: string) => x.split("\n\n"))
  .map((x: string) => ({
    title: x[1].split("\n")[0].replace("// ", ""),
    basis: x[2],
  }));

export const App: FC = () => {
  const [input, setInput] = useState(questions[0].basis);
  const [result, setResult] = useState<string[]>([]);
  const [resultTime, setResultTime] = useState<number>(0);
  useEffect(() => {
    void (async () => {
      try {
        const startTime = performance.now();
        const result = groebnerBasisString(
          input.replace(/\r\n/g, "\n").replace(/\n/g, ",")
        );
        setResult(result.split(","));
        setResultTime(performance.now() - startTime);
      } catch (e) {
        console.error(e);
      }
    })();
  }, [input]);

  const [displayMode, setDisplayMode] = useState<"katex" | "text">("katex");
  const onChangeDisplayModeRadio = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDisplayMode(e.target.value as "katex" | "text");
  };

  return (
    <div className="wrapper">
      <h1>Gröbner basis</h1>

      <h2>input</h2>
      <textarea value={input} onChange={(e) => setInput(e.target.value)} />
      <label>
        presets:
        <select
          onChange={(e) => {
            const question = questions.find((x) => x.title === e.target.value);
            if (question) setInput(question.basis);
          }}
        >
          {questions.map((x) => (
            <option value={x.title} key={x.title}>
              {x.title}
            </option>
          ))}
        </select>
      </label>

      <h2>result</h2>
      <p>{resultTime} ms</p>

      <p className="displayMode">
        <label>
          <input
            type="radio"
            name="displayMode"
            value="katex"
            defaultChecked
            onChange={onChangeDisplayModeRadio}
          />
          katex
        </label>
        <label>
          <input
            type="radio"
            name="displayMode"
            value="text"
            onChange={onChangeDisplayModeRadio}
          />
          text
        </label>
      </p>

      {displayMode === "katex" && (
        <ul>
          {result.map((x) => (
            <li key={x}>
              <InlineMath math={x} />
            </li>
          ))}
        </ul>
      )}
      {displayMode === "text" && (
        <ul>
          {result.map((x) => (
            <li key={x}>{x}</li>
          ))}
        </ul>
      )}
    </div>
  );
};