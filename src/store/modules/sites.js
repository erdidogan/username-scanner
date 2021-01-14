const namespaced = true

const state = {
    sites: []
}

const getters = {
    getSites: state => {
        return state.sites
    }
}

const mutations = {
    changeSites: (state, payload) => {
        state.sites.push(payload)
    },
    clearSites: (state) => {
        state.sites = [];
    }
}

const actions = {
    setSites: ({commit}, payload) => {
        commit('changeSites', payload)
    },
    clearSites: ({commit}) => {
        commit('clearSites')
    }
}

export default {
    namespaced,
    state,
    getters,
    mutations,
    actions
}
