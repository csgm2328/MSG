import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './user';

export default new Vuex.Store({
  modules: {
    user,
  },
  plugins: [
    createPersistedState({
      paths: ['user'],
    }),
  ],
});
