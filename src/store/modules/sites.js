const namespaced = true

const state = {
    sites: [],
    postSites:[]
}

const getters = {
    getSites: state => {
        return state.sites
    },
    getSitesForPost: state => {
        return state.postSites
    }
}

const mutations = {
    changeSites: (state, payload) => {
        state.sites.push(payload)
    },
    changeSitesForPost: (state, payload) => {
        state.postSites.push(payload)
    },
    clearSites: (state) => {
        state.sites = [];
        state.postSites = [];
    }
}

const actions = {
    setSites: ({commit}, payload) => {
        commit('changeSites', payload)
    },
    setSitesForPost: ({commit}, payload) => {
        commit('changeSitesForPost', payload)
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
