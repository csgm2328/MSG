const state = {
    store: null,
  };
  
  const getters = {
    store(state) {
      return state.store;
    },
  };
  
  const mutations = {
    SET_STORE(state, payload) {
      state.store  = payload;
    },
  };
  
  const actions = {
    async set_store(context, data) {
      await context.commit('SET_STORE', data);
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
  