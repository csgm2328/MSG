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

function getSearchWithDong(data, callback, errorCallback) {
    
    axiosService
        .get('/search', { params: data })
        .then((res) => {
            callback(res.data);
        })
        .catch((err) => {
            errorCallback(err);
        });

}

function updateSearch(search, callback, errorCallback) {
    axiosService
        .get('/search/updateCnt', { params: { name: search.name, area: search.area } })
        .then((res) => {
            callback(res.data);
        })
        .catch((err) => {
            errorCallback(err);
        });
}

export { getSearch, getSearchWithDong, updateSearch };
