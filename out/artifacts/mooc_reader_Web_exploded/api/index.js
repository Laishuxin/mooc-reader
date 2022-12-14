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

function getBooks(params = {}) {
  return get({
    url: '/api/book/list',
    params,
  })
}

function getBookById(params) {
  const { id } = params
  return get({
    url: `/api/book/${id}`,
  }).then((res) => res.book)
}

function getEvaluations(params) {
  return get({
    url: '/api/evaluation/list',
    params,
  }).then((res) => res.list)
}

function login(params) {
  return post({
    url: '/api/member/login',
    data: params,
  }).then((res) => res.member)
}

function register(params) {
  return post({
    url: '/api/member/register',
    data: params,
  })
}

function getMember(params) {
  const { memberId } = params
  return get({
    url: `/api/member/${memberId}`,
  }).then((res) => res.member)
}

function getReadState(params) {
  return get({
    url: '/api/member/read_state',
    params,
  }).then((res) => res.readState)
}

function updateReadState(params) {
  return post({
    url: `/api/member/read_state`,
    data: params,
  }).then((res) => res.readState)
}

function addEvaluation(params) {
  return post({
    url: `/api/evaluation/add`,
    data: params,
  })
}

function enjoy(params) {
  return post({
    url: '/api/evaluation/enjoy',
    data: params,
  }).then((res) => res.evaluation)
}

window.http = {
  get,
  post,
  put,
  remove,
  request,

  getCategory,
  getBooks,
  getBookById,

  getEvaluations,
  addEvaluation,

  register,
  login,
  getMember,

  getReadState,
  updateReadState,
  enjoy,
}
