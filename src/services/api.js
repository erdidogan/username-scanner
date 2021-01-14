let api = {}
import axios from 'axios'
import {LoadingProgrammatic as Loading, SnackbarProgrammatic as Snackbar} from 'buefy'


api.install = function (Vue, options) {
    let baseURL = options.baseURL;

    Vue.prototype.$apiCall = (url) => {
        clearSites()
        const loadingComponent = Loading.open({})
        axios.get(baseURL + '/api' + url)
            .then(function (response) {
                handleResponse(response)
            })
            .catch(function (error) {
                Snackbar.open('Data Fetch Error!  ' + error)
            }).then(function () {
            loadingComponent.close()
        });
    }

    function handleResponse(response) {
        options.store.dispatch('sites/setSites', response.data)
    }

    function clearSites() {
        options.store.dispatch('sites/clearSites', [])
    }
}

export default api
