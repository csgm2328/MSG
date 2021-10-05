const state = {
    store: null,
    vsStore: null,
  };
  
  const getters = {
    store(state) {
      return state.store;
    },
    vsStore(state) {
      return state.vsStore;
    },
  };
  
  const mutations = {
    SET_STORE(state, payload) {
      state.store  = payload;
    },
    SET_VSSTORE(state, payload) {
      state.vsStore  = payload;
    },
  };
  
  const actions = {
    async set_store(context, data) {
      await context.commit('SET_STORE', data);
    },
    async set_vsStore(context, data) {
      await context.commit('SET_VSSTORE', data);
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
  