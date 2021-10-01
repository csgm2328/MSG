import { axiosService } from '@/api/index.js';

async function kakaoLoginUser(userData, callback, errorCallback) {
  await axiosService
    .get('/member/kakaoLogin', { params: { authorizeCode: userData } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function nicknameCheck(userData, callback, errorCallback) {
  await axiosService
    .get('/member/ncheck', { params: { nickname: userData } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function signUpUser(userData, callback, errorCallback) {
  await axiosService
    .post('/member/signup', userData)
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function loginUser(userData, callback, errorCallback) {
  await axiosService
    .post('/member/login', userData)
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

export { kakaoLoginUser, loginUser, signUpUser, nicknameCheck };
