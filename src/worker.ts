// @ts-ignore
// prettier-ignore
import { groebnerBasisString, sampleQuestions } from "../groebner/build/js/packages/groebner-composeApp/kotlin/groebner-composeApp.mjs";

self.addEventListener("message", (e) => {
  const { taskId, basisString }: { taskId: number; basisString: string } =
    e.data;

  try {
    const resultBasisString = groebnerBasisString(basisString);
    self.postMessage({ taskId, basisString: resultBasisString });
  } catch (error) {
    self.postMessage({ taskId, error });
  }
});

self.postMessage({ taskId: 0, ready: true });
