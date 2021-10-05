import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import user from "./user";
import header from "./header";
import loading from "./loading";
import keyword from "./keyword";

export default new Vuex.Store({
  modules: {
    user,
    header,
    loading,
    keyword,
  },
  plugins: [
    createPersistedState({
      paths: ["user", "header", "loading", "keyword"],
    }),
  ],
});
