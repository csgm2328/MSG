import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './user';
import header from './header';

export default new Vuex.Store({
  modules: {
    user,
    header,
  },
  plugins: [
    createPersistedState({
      paths: ['user', 'header'],
    }),
  ],
});
