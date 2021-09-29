const state = {
  isOpen: false,
};

const getters = {
  isOpen(state) {
    return state.isOpen;
  },
  // get_id: (state) => state.id,
};

const mutations = {
  TOGGLE_ISOPEN(state, payload) {
    state.isOpen = payload;
  },
};

const actions = {
  toggle_isOpen(context, data) {
    context.commit('TOGGLE_ISOPEN', data);
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
