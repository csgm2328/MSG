import { axiosService } from '@/api/index.js';

function loginUser(userData, callback, errorCallback) {
  axiosService
    .get('/member', { params: { authorizeCode: userData } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

export { loginUser };
