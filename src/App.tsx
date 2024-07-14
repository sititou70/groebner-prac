import { FC, useEffect, useRef, useState } from "react";
import "./App.css";
import { InlineMath } from "react-katex";
import "katex/dist/katex.min.css";
import Worker from "./worker?worker";

// @ts-ignore
// prettier-ignore
import { groebnerBasisString, sampleQuestions } from "../groebner/build/js/packages/groebner-composeApp/kotlin/groebner-composeApp.mjs";

const questions: { title: string; comments: string; basis: string }[] =
  sampleQuestions
    .get()
    .split("---")
    .map((x: string) => x.split("\n\n"))
    .map((x: string) => ({
      title: x[1].split("\n")[0].replace("// ", ""),
      comments: x[1],
      basis: x[2],
    }));

const useThrottle = (ms: number, callback: VoidFunction) => {
  const timerRef = useRef(0);

  return () => {
    clearTimeout(timerRef.current);
    timerRef.current = setTimeout(() => {
      callback();
    }, ms);
    return;
  };
};

export const App: FC = () => {
  const getWorker = () =>
    new Promise<Worker>((resolve) => {
      const worker = new Worker();
      worker.addEventListener("message", (e) => {
        if ("ready" in e.data) resolve(worker);
      });
    });

  const [input, setInput] = useState(
    questions[0].comments + "\n\n" + questions[0].basis
  );
  const [resultBasis, setResultBasis] = useState<string[]>([]);
  const [taskInfo, setTaskInfo] = useState("");
  const calculatingRef = useRef<{
    worker: Worker | null;
    calculating: boolean;
    timer: number;
  }>({
    worker: null,
    calculating: false,
    timer: 0,
  });
  const onInputChange = async () => {
    let worker: Worker;
    if (calculatingRef.current.worker && !calculatingRef.current.calculating) {
      worker = calculatingRef.current.worker;
    } else {
      calculatingRef.current.worker?.terminate();
      clearInterval(calculatingRef.current.timer);

      worker = await getWorker();
      calculatingRef.current.worker = worker;
    }

    calculatingRef.current.calculating = true;
    const taskId = Math.random();
    const startTime = performance.now();
    const getWorkingTime = () => {
      return Math.round(performance.now() - startTime);
    };
    const taskInfoTimer = setInterval(() => {
      setTaskInfo(`calculating... (${getWorkingTime()} ms)`);
    }, 500);
    calculatingRef.current.timer = taskInfoTimer;

    worker.addEventListener("message", (e) => {
      if (e.data.taskId !== taskId) return;

      if ("error" in e.data) {
        console.error(e.data.error);
        setTaskInfo(`error`);
      }

      if ("basisString" in e.data) {
        setResultBasis(e.data.basisString.split(","));
        setTaskInfo(`done (${getWorkingTime()} ms)`);
      }

      calculatingRef.current.calculating = false;
      clearInterval(taskInfoTimer);
    });

    const basisString = input
      .replace(/\r\n/g, "\n")
      .split("\n")
      .filter((x) => x !== "")
      .filter((x) => !x.startsWith("//"))
      .join(",");
    worker.postMessage({ taskId, basisString });
  };
  const onInputChangeThrottled = useThrottle(300, onInputChange);
  useEffect(() => {
    onInputChangeThrottled();
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
            if (question) setInput(question.comments + "\n\n" + question.basis);
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
      <p>{taskInfo}</p>

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
          {resultBasis.map((x) => (
            <li key={x}>
              <InlineMath math={x.replace(/\^([0-9]+)/g, "^{$1}")} />
            </li>
          ))}
        </ul>
      )}
      {displayMode === "text" && (
        <ul>
          {resultBasis.map((x) => (
            <li key={x}>{x}</li>
          ))}
        </ul>
      )}
    </div>
  );
};
