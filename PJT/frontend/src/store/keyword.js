const state = {
  words: [],
};

const getters = {
  getWords(state) {
    return state.words;
  },
  // get_id: (state) => state.id,
};

const mutations = {
  GET_WORDS(state, payload) {
    state.words = payload;
  },
};

const actions = {
  async get_words(context, data) {
    await context.commit("GET_WORDS", data);
  },
};

export default {
  strict: process.env.NODE_ENV !== "production",
  state: {
    ...state,
  },
  getters,
  mutations,
  actions,
};
