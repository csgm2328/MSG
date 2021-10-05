import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './user';
import header from './header';
import loading from './loading';
import store from './store';

export default new Vuex.Store({
  modules: {
    user,
    header,
    loading,
    store,
  },
  plugins: [
    createPersistedState({
      paths: ['user', 'header', 'loading', 'store'],
    }),
  ],
});
