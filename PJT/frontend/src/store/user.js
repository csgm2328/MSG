const state = {
  isLogin: false,
};

const getters = {
  isLogin(state) {
    return state.isLogin;
  },
  // get_id: (state) => state.id,
};

const mutations = {
  TOGGLE_ISLOGIN(state, payload) {
    state.isLogin = payload;
  },
};

const actions = {
  toggle_isLogin(context, data) {
    context.commit('TOGGLE_ISLOGIN', data);
  },
};

export default {
  strict: process.env.NODE_ENV !== 'production',
  state: {
    ...state,
  },
  getters,
  mutations,
  actions,
};
