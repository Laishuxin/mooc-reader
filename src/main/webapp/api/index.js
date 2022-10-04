const axios = window.axios

const http = axios.create()

http.interceptors.response.use((res) => {
  const remoteData = res.data
  const { code, msg, data } = remoteData
  if (code == '0') {
    return data
  } else {
    throw new Error(msg)
  }
})

/**
 * @returns { Promise }
 */
function request(method = 'get', config = {}) {
  return http({
    method,
    ...config,
  })
}

function get(config = {}) {
  return request('get', config)
}

function post(config = {}) {
  return request('post', config)
}

function put(config = {}) {
  return request('put', config)
}

function remove(config = {}) {
  return request('delete', config)
}

function getCategory() {
  return get({
    url: '/api/category/list',
  }).then((res) => res.list)
}

window.http = {
  get,
  post,
  put,
  remove,
  request,

  getCategory,
}
