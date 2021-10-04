import { axiosService, axiosServiceWithAuth } from '@/api/index.js';

function addReview(review, callback, errorCallback) {
  axiosServiceWithAuth
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

function getReviewInMypage(useData, callback, errorCallback) {
  axiosServiceWithAuth
    .get('/review/userReviewList', { params: { mid: useData } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

function getReview(mid, callback, errorCallback) {
  axiosService
    .get('/review/userReviewList', { params: { mid: mid } })
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

function delReview(rid, callback, errorCallback) {
  console.log('rid : ', rid);
  axiosServiceWithAuth
    .put('/review/deleteReview/' + rid)
    .then((res) => {
      callback(res.data);
    })
    .catch((err) => {
      errorCallback(err);
    });
}

export { addReview, getReview, delReview, getReviewInMypage };
