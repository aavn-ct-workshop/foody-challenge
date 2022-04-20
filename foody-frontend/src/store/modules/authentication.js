const state = {
  accessToken: '',
  username: ''
};

const getters = {
  getAccessToken: state => state.accessToken,
  getUserName: state => state.username
};

const mutations = {
  updateAccessToken(state, param) {
    state.accessToken = param;
  },
  updateUserName(state, param) {
    state.username = param;
  }
};

const actions = {
  updateAccessToken(context, accessToken) {
    context.commit('updateAccessToken', accessToken)
  },
  updateUserName(context, username) {
    context.commit('updateUserName', username)
  }
};

export const authentication = {
  namespaced: true,
  state,
  actions,
  mutations,
  getters
};
