import { axiosService } from '@/api/index.js';

function getSearch(search, callback, errorCallback) {
    
    axiosService
        .get('/search', { params: { name: search } })
        .then((res) => {
            callback(res.data);
        })
        .catch((err) => {
            errorCallback(err);
        });

}

function updateSearch(search, callback, errorCallback) {
    axiosService
        .put('/search/updateCnt', search )
        .then((res) => {
            callback(res.data);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

export { getSearch, updateSearch };
