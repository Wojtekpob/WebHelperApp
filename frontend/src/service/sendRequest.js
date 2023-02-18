import { useLocalState } from "./useLocalStorage";

function sendRequest(url, method, jwt, requestBody) {
  const data = {
    headers: {
      "Content-Type": "application/json",
    },
    method: method,
  };

  if (jwt) {
    data.headers.Authorization = `Bearer ${jwt}`;
  }

  if (requestBody) {
    data.body = JSON.stringify(requestBody);
  }

  return fetch(url, data).then((response) => {
    if (response.status === 200) return response.json();
  });
}

export default sendRequest;
