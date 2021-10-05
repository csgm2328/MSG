import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './user';
import header from './header';
import loading from './loading';
import page from './page';

export default new Vuex.Store({
  modules: {
    user,
    header,
    loading,
    page,
  },
  plugins: [
    createPersistedState({
      paths: ['user', 'header', 'loading', 'page'],
    }),
  ],
});
