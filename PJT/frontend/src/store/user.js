const state = {
  isLogin: false,
  signupNickname: '',
};

const getters = {
  isLogin(state) {
    return state.isLogin;
  },
  signupNickname(state) {
    return state.signupNickname;
  },
  // get_id: (state) => state.id,
};

const mutations = {
  TOGGLE_ISLOGIN(state, payload) {
    state.isLogin = payload;
  },
  SET_SIGNUPNICKNAME(state, payload) {
    state.signupNickname = payload;
  },
};

const actions = {
  toggle_isLogin(context, data) {
    context.commit('TOGGLE_ISLOGIN', data);
  },
  toggle_isLoginToSideBar(context, data) {
    setTimeout(function () {
      context.commit('TOGGLE_ISLOGIN', data);
    }, 350);
  },
  set_signupnickname(context, data) {
    context.commit('SET_SIGNUPNICKNAME', data);
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
