import axios from 'axios';

function createAxiosService() {
  return axios.create({
    baseURL: 'localhost: 8080',
    withCredentials: true,
    headers: {
      'Content-type': 'application/json',
    },
  });
}

export const axiosService = createAxiosService();
