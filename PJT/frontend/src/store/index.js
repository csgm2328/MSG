import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './user';
import header from './header';
import loading from './loading';

export default new Vuex.Store({
  modules: {
    user,
    header,
    loading,
  },
  plugins: [
    createPersistedState({
      paths: ['user', 'header', 'loading'],
    }),
  ],
});
