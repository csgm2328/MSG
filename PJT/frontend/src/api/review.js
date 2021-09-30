import { axiosService } from '@/api/index.js';

function addReview(review, callback, errorCallback) {
  
    axiosService
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

function getReview(mid, callback, errorCallback) {
  axiosService
      .get('/review/userReviewList', {params :{"mid" : mid}})
      .then((res) => {
          callback(res.data);
      })
      .catch((err) => {
          errorCallback(err);
      });
}

function delReview(rid, callback, errorCallback) {
  console.log("rid : ", rid)
  axiosService
      .put('/review/deleteReview/' + rid)
      .then((res) => {
          callback(res.data);
      })
      .catch((err) => {
          errorCallback(err);
      });
}

export { addReview, getReview, delReview};
