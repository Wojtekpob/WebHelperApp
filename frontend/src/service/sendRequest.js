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
    if (response.ok) return response.json();
    else return Promise.reject(response.statusText);
  });
}

export default sendRequest;
