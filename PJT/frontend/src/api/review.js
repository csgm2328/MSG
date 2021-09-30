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

export { addReview };
