import { axiosService, axiosServiceWithAuth } from '@/api/index.js';

async function addReview(review, callback, errorCallback) {
  await axiosServiceWithAuth
    .post('/review/addReview', review, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function getReviewInMypage(userData, callback, errorCallback) {
  await axiosServiceWithAuth
    .get(
      `/review/userReviewList?mid=${userData.mid}&page=${userData.page}&size=${userData.size}&sort=${userData.sort}&sort=rid,desc`
    )
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function getReview(mid, callback, errorCallback) {
  await axiosService
    .get('/review/userReviewList', { params: { mid: mid } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function delReview(rid, callback, errorCallback) {
  await axiosServiceWithAuth
    .put('/review/deleteReview/' + rid)
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

async function getReviewCnt(userData, callback, errorCallback) {
  await axiosServiceWithAuth
    .get(`/review/getReviewCnt?mid=${userData}`)
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

export { addReview, getReview, delReview, getReviewInMypage, getReviewCnt };
